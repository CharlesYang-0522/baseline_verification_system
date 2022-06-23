# Account_Baseline

# 系统账户基线检查及设置，因获取信息量较大，固为耗时操作
import subprocess
import winreg

import wmi
import uuid
import json

wmiobj = wmi.WMI()  # 实例化wmi类


class AccountBaseline:

    def __init__(self):
        self.wmi_Win32_UserAccount = wmiobj.Win32_UserAccount()

    def getMachineGuid(self):
        # 1. 连接注册表根键
        root1 = winreg.ConnectRegistry(None, winreg.HKEY_LOCAL_MACHINE)
        result = {}
        try:
            # 2. 指定想要访问的子健
            reg_path = r"SOFTWARE\Microsoft\Cryptography"
            # 3. 打开相应子健
            key1 = winreg.OpenKey(root1, reg_path)  # 打开localmachine的autorun列表
            try:
                count = 0
                while (1):
                    try:
                        # 4. 通过winreg对象的EnumValue()方法迭代其中的键值
                        n, v, t = winreg.EnumValue(key1, count)
                        result[n] = v
                        count += 1
                    except EnvironmentError:
                        break
            finally:
                # 5. 关闭相应子健
                winreg.CloseKey(key1)
        finally:
            # 6. 关闭相应根键连接
            winreg.CloseKey(root1)
        return result['MachineGuid']

    def run_code(self, cmd):
        res = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
        for line in res.communicate():
            return (line)

    def get_user_json(self):
        m_list = []
        for user in self.wmi_Win32_UserAccount:
            m = {}
            responce_obj = str(user)
            responce_obj = responce_obj.replace('\n', '').replace(";", "").replace('"', '').replace(' ', '')[:-1]
            responce_obj = responce_obj.split('\t')
            for i in responce_obj[1:]:
                x = i.split('=')
                m[x[0]] = x[1]
            m_list.append(m)
        return m_list

    def get_json(self):
        m = {}
        m["MachineGuid"] = self.getMachineGuid()
        m["type"] = "Account_Baseline"
        m["describe"] = "账户基线扫描结果"
        m["user"] = self.get_user_json()
        AccountBaseline = json.dumps(m)
        # print(Account_Baseline)
        return AccountBaseline

    def get_AccountBaseline_json(self):
        old_json = self.get_json()
        old_json = json.loads(old_json)
        new_json = {}
        new_json['MachineGuid'] = old_json['MachineGuid']
        new_json["type"] = old_json["type"]
        new_json["describe"] = old_json["describe"]
        account_list = []
        for i in range(len(old_json["user"])):
            account_json = {}
            # x = old_json["user"][i]["Caption"]
            account_json["Caption"] = old_json["user"][i]["Caption"]
            account_json["Description"] = old_json["user"][i]["Description"]
            account_json["Domain"] = old_json["user"][i]["Domain"]
            account_json["Name"] = old_json["user"][i]["Name"]
            account_json["PasswordRequired"] = old_json["user"][i]["PasswordRequired"]
            account_json["SID"] = old_json["user"][i]["SID"]
            account_json["Status"] = old_json["user"][i]["Status"]
            account_list.append(account_json)
        new_json["user"] = account_list
        ServiceBaseline = json.dumps(new_json)
        return ServiceBaseline


if __name__ == '__main__':
    c = AccountBaseline()
    print(c.get_AccountBaseline_json())
