# System_Baseline
import subprocess
import ast
import winreg

import wmi
import uuid
import json

TIME_FORMAT = r'%Y-%m-%d %H:%M:%S'

# 实例化wmi类
wmiobj = wmi.WMI()


class SystemBaseline:
    def __init__(self):

        self.new_json = {}
        self.new_json['MachineGuid'] = self.getMachineGuid()
        self.new_json["type"] = "System_Baseline"
        self.new_json["describe"] = "系统基线扫描结果"
        # 创建wmi实例
        self.obj = wmiobj.Win32_OperatingSystem()[0]  # 用于获取计算机运行环境信息
        self.cobj = wmiobj.Win32_ComputerSystem()[0]  # 用于获取计算机CPU数量,内存大小,主板相关信息
        self.disk_obj = wmiobj.Win32_DiskDrive()  # 用于获取硬盘相关信息
        self.Partition_obj = wmiobj.Win32_LogicalDisk()  # 用于获取分区相关信息
        self.networkAdapter_obj = wmiobj.Win32_NetworkAdapterConfiguration(IPEnabled=1)  # 用于配置及获取网络连接相关信息
        self.process_obj = wmiobj.Win32_Processor()[0]  # 用于获取CPU详细信息
        self.update_obj = wmiobj.Win32_QuickFixEngineering()  # 用于获取windows更新补丁相关信息

    def get_obj_json(self):
        m = {}
        responce_obj = str(self.obj)
        responce_obj = responce_obj.replace('\n', '').replace(";", "").replace('"', '').replace(' ', '')[:-1]
        responce_obj = responce_obj.split('\t')
        for i in responce_obj[1:]:
            x = i.split('=')
            m[x[0]] = x[1]
        self.new_json["OSCaption"] = m["Caption"]
        self.new_json["OSVersion"] = m["Version"]

    def get_cobj_json(self):
        m = {}
        responce_obj = str(self.cobj)
        responce_obj = responce_obj.replace('\n', '').replace(";", "").replace('"', '').replace(' ', '')[:-1]
        responce_obj = responce_obj.split('\t')
        for i in responce_obj[1:]:
            x = i.split('=')
            m[x[0]] = x[1]
        return m

    def get_disk_obj_json(self):
        m = {}
        for disk in self.disk_obj:
            responce_obj = str(disk)
            responce_obj = responce_obj.replace('\n', '').replace(";", "").replace('"', '').replace(' ', '')[:-1]
            responce_obj = responce_obj.split('\t')
            for i in responce_obj[1:]:
                x = i.split('=')
                m[x[0]] = x[1]

        self.new_json["DiskCaption"] = m["Caption"]
        self.new_json["InterfaceType"] = m["InterfaceType"]

    def get_Partition_obj_json(self):
        m_list = []
        for Partition in self.Partition_obj:
            m = {}
            responce_obj = str(Partition)
            responce_obj = responce_obj.replace('\n', '').replace(";", "").replace('"', '').replace(' ', '')[:-1]
            responce_obj = responce_obj.split('\t')
            for i in responce_obj[1:]:
                x = i.split('=')
                m[x[0]] = x[1]
            m_list.append(m)


    def get_networkAdapter_obj_json(self):
        m_list = []
        for networkAdapter in self.networkAdapter_obj:
            m = {}
            responce_obj = str(networkAdapter)
            responce_obj = responce_obj.replace('\n', '').replace(";", "").replace('"', '').replace(' ', '')[:-1]
            responce_obj = responce_obj.split('\t')
            for i in responce_obj[1:]:
                x = i.split('=')
                m[x[0]] = x[1]
            m_list.append(m)
        m = m_list[0]
        self.new_json["networkCaption"] = m["Description"]
        self.new_json["IPAddress"] = m["IPAddress"].split(',')[0].strip('{')
        self.new_json["MACAddress"] = m["MACAddress"]

    def get_process_obj_json(self):
        m = {}
        responce_obj = str(self.process_obj)
        responce_obj = responce_obj.replace('\n', '').replace(";", "").replace('"', '').replace(' ', '')[:-1]
        responce_obj = responce_obj.split('\t')
        for i in responce_obj[1:]:
            x = i.split('=')
            m[x[0]] = x[1]
        return m

    def get_update_obj_json(self):
        m_list = []
        for update in self.update_obj:
            m = {}
            responce_obj = str(update)
            responce_obj = responce_obj.replace('\n', '').replace(";", "").replace('"', '').replace(' ', '')[:-1]
            responce_obj = responce_obj.split('\t')
            for i in responce_obj[1:]:
                x = i.split('=')
                m[x[0]] = x[1]
            m_list.append(m)
        m = m_list[0]
        self.new_json["UpdateHotFixID"] = m["HotFixID"]
        self.new_json["InstalledOn"] = m["InstalledOn"]

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

    def get_SystemBaseline_json(self):
        self.get_obj_json()
        self.get_disk_obj_json()
        self.get_networkAdapter_obj_json()
        self.get_update_obj_json()
        SystemBaseline = json.dumps(self.new_json)
        return SystemBaseline


if __name__ == '__main__':
    s = SystemBaseline()
    print(s.get_SystemBaseline_json())
