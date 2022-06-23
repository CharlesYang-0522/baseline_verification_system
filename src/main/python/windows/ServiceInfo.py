# Service_Baseline
import subprocess
import winreg

import wmi
import uuid
import json

wmiobj = wmi.WMI()  # 实例化wmi类


class ServiceBaseline:
    def __init__(self):
        self.wmi_Win32_Service = wmiobj.Win32_Service()

    def get_service_json(self):
        m_list = []
        for service in self.wmi_Win32_Service:
            m = {}
            responce = str(service)
            responce = responce.replace('\n', '').replace(";", "").replace('"', '').replace(' ', '')[:-1]
            responce = responce.split('\t')
            for i in responce[1:]:
                x = i.split('=')
                m[x[0]] = x[1]
            m_list.append(m)
        return m_list

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

    def get_json(self):
        m = {}
        m["MachineGuid"] = self.getMachineGuid()
        m["type"] = "Service_Baseline"
        m["describe"] = "服务基线扫描结果"
        m["service"] = self.get_service_json()
        ServiceBaseline = json.dumps(m)
        # print(ServiceBaseline)
        return ServiceBaseline

    def get_ServiceBaseline_json(self, time):
        old_json = self.get_json()
        old_json = json.loads(old_json)
        new_json = {}
        new_json['MachineGuid'] = old_json['MachineGuid']
        new_json["type"] = old_json["type"]
        new_json["describe"] = old_json["describe"]
        new_json['time'] = time
        for i in range(10):
            service_json = {}
            x = old_json["service"][i]["Caption"]
            service_json["AcceptPause"] = old_json["service"][i]["AcceptPause"]
            service_json["AcceptStop"] = old_json["service"][i]["AcceptStop"]
            service_json["Description"] = old_json["service"][i]["Description"]
            service_json["ErrorControl"] = old_json["service"][i]["ErrorControl"]
            service_json["PathName"] = old_json["service"][i]["PathName"]
            service_json["ProcessId"] = old_json["service"][i]["ProcessId"]
            service_json["ServiceType"] = old_json["service"][i]["ServiceType"]
            service_json["Started"] = old_json["service"][i]["Started"]
            service_json["StartMode"] = old_json["service"][i]["StartMode"]
            service_json["State"] = old_json["service"][i]["State"]
            service_json["Status"] = old_json["service"][i]["Status"]
            new_json[x] = service_json
        ServiceBaseline = json.dumps(new_json)
        return ServiceBaseline

if __name__ == '__main__':
    s = ServiceBaseline()
    print(s.get_ServiceBaseline_json(1))
