# Hardware_Baseline

#  获取所有系统硬件基线信息

import datetime
import subprocess
import uuid
import winreg

import wmi
import json

wmiobj = wmi.WMI()  # 实例化wmi类


class HardwareBaseline:
    def __init__(self):
        self.wmi_Win32_OperatingSystem = wmiobj.Win32_OperatingSystem()[0]  # 获取计算机运行环境信息

        self.info_os = self.wmi_Win32_OperatingSystem.Caption  # 获取系统版本

        self.info_version = self.wmi_Win32_OperatingSystem.CSDVersion  # 操作系统更新版本

        self.info_fullname = self.wmi_Win32_OperatingSystem.CSName  # 获取计算机名

        self.info_localtime = datetime.datetime.strptime(
            str(str(self.wmi_Win32_OperatingSystem.LocalDateTime).split('.')[0]),
            '%Y%m%d%H%M%S')  # 获取系统本地时间

        self.info_lastboottime = datetime.datetime.strptime(
            str(str(self.wmi_Win32_OperatingSystem.LastBootUpTime).split('.')[0]),
            '%Y%m%d%H%M%S')  # 获取系统上次启动时间

        self.info_os_architecture = self.wmi_Win32_OperatingSystem.OSArchitecture  # 获取操作系统类型(32bit/64bit)

        self.info_mu_languages = self.wmi_Win32_OperatingSystem.MUILanguages[0]  # 获取操作系统语言版本

        self.info_SerialNumber = self.wmi_Win32_OperatingSystem.SerialNumber  # 获取操作系统序列号

        self.wmi_Win32_ComputerSystem = wmiobj.Win32_ComputerSystem()[0]  # 获取计算机CPU数量,内存大小,主板相关信息

        self.info_cpu_count = self.wmi_Win32_ComputerSystem.NumberOfProcessors  # 获取cpu数量 这里获取的是外部硬件规格，家用机电脑，主板只能安装一个cpu规格，所有获取为1

        self.info_mainboard = self.wmi_Win32_ComputerSystem.Manufacturer  # 获取主板厂商信息

        self.info_board_model = self.wmi_Win32_ComputerSystem.Model  # 获取主板型号

        self.info_systemtype = self.wmi_Win32_ComputerSystem.SystemType  # 获取主板架构类型

        self.info_physical_memory = int(
            self.wmi_Win32_ComputerSystem.TotalPhysicalMemory) / 1024 / 1024 / 1024  # 获取内存容量
        self.info_physical_memory = str(int(self.info_physical_memory) + 1) + 'G'

        self.wmi_Win32_Processor = wmiobj.Win32_Processor()[0]  # 用于获取CPU详细信息

        self.info_cpu_name = self.wmi_Win32_Processor.Name  # 获取cpu类型

        self.info_clock_speed = self.wmi_Win32_Processor.MaxClockSpeed  # 获取操作系统主频

        self.info_number_core = self.wmi_Win32_Processor.NumberOfCores  # 获取核心数量

        self.info_data_width = self.wmi_Win32_Processor.DataWidth  # 获取计算机的CPU数据宽度

        self.info_socket_desigination = self.wmi_Win32_Processor.SocketDesignation  # 获取主板cpu接口类型

        self.info_l2_cache = self.wmi_Win32_Processor.L2CacheSize  # 获取cpu二级缓存大小

        self.info_l3_cache = self.wmi_Win32_Processor.L3CacheSize  # 获取cpu三级缓存大小

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

    def get_HardwareBaseline_json(self):
        m = {}
        m["MachineGuid"] = self.getMachineGuid()
        m["type"] = "Hardware_Baseline"
        m["describe"] = "硬件基线扫描结果"
        m['info_os'] = str(self.info_os)
        m['info_os_architecture'] = str(self.info_os_architecture)
        m['info_version'] = str(self.info_version)
        m['info_fullname'] = str(self.info_fullname)
        m['info_mu_languages'] = str(self.info_mu_languages)
        m['info_SerialNumber'] = str(self.info_SerialNumber)
        m['info_cpu_count'] = str(self.info_cpu_count)
        m['info_mainboard'] = str(self.info_mainboard)
        m['info_board_model'] = str(self.info_board_model)
        m['info_systemtype'] = str(self.info_systemtype)
        m['info_physical_memory'] = str(self.info_physical_memory)
        m['info_cpu_name'] = str(self.info_cpu_name)
        m['info_clock_speed'] = str(self.info_clock_speed)
        m['info_number_core'] = str(self.info_number_core)
        m['info_data_width'] = str(self.info_data_width)
        m['info_socket_desigination'] = str(self.info_socket_desigination)
        m['info_l2_cache'] = str(self.info_l2_cache)
        m['info_l3_cache'] = str(self.info_l3_cache)

        HardwareBaseline = json.dumps(m)
        # print(Hardware_Baseline)
        return HardwareBaseline


if __name__ == '__main__':
    h = HardwareBaseline()
    print(h.get_HardwareBaseline_json())
