# Baseline

import configparser
import ast
import ctypes
import json
import subprocess
import sys
import winreg

class NewConfigParser(configparser.ConfigParser):
    def optionxform(self, optionstr):
        return optionstr

class Baseline:
    def __init__(self):
        self.sql = {
            "account": [
                {
                    "name": "ResetLockoutCount",
                    "state": "danger",
                    "machineguid": "",
                    "importance": "High",
                    "standardvalue": 15,
                    "actualvalue": "",
                    "comparemethod": "more",
                    "description": "Reset account lockout counter after"
                },
                {
                    "name": "LockoutBadCount",
                    "state": "danger",
                    "machineguid": "",
                    "importance": "Middle",
                    "standardvalue": "[0,10]",
                    "actualvalue": "",
                    "comparemethod": "between",
                    "description": "Account lockout threshold"
                },
                {
                    "name": "LockoutDuration",
                    "state": "danger",
                    "machineguid": "",
                    "importance": "Low",
                    "standardvalue": 15,
                    "actualvalue": "",
                    "comparemethod": "more",
                    "description": "Account lockout duration"
                },
                {
                    "name": "PasswordHistorySize",
                    "state": "danger",
                    "machineguid": "",
                    "importance": "High",
                    "standardvalue": 24,
                    "actualvalue": "",
                    "comparemethod": "more",
                    "description": "Enforce password history"
                },
                {
                    "name": "MaximumPasswordAge",
                    "state": "danger",
                    "machineguid": "",
                    "importance": "High",
                    "standardvalue": 60,
                    "actualvalue": "",
                    "comparemethod": "less",
                    "description": "'Maximum password age"
                },
                {
                    "name": "SeNetworkLogonRight",
                    "state": "danger",
                    "machineguid": "",
                    "importance": "Low",
                    "standardvalue": ["*S-1-5-32-544", "*S-1-5-32-545", "*S-1-5-32-551"],
                    "actualvalue": "",
                    "comparemethod": "equal",
                    "description": "Access this computer from the network"
                },
                {
                    "name": "SeSystemtimePrivilege",
                    "state": "danger",
                    "machineguid": "",
                    "importance": "Low",
                    "standardvalue": ["*S-1-5-19", "*S-1-5-32-544"],
                    "actualvalue": "",
                    "comparemethod": "equal",
                    "description": "Change the system time"
                },
                {
                    "name": "SeManageVolumePrivilege",
                    "state": "danger",
                    "machineguid": "",
                    "importance": "Low",
                    "standardvalue": ["*S-1-5-32-544"],
                    "actualvalue": "",
                    "comparemethod": "equal",
                    "description": "Add workstations to domain"
                },
                {
                    "name": "SeIncreaseQuotaPrivilege",
                    "state": "danger",
                    "machineguid": "",
                    "importance": "Low",
                    "standardvalue": ["*S-1-5-32-544", "*S-1-5-19", "*S-1-5-20"],
                    "actualvalue": "",
                    "comparemethod": "equal",
                    "description": "Adjust memory quotas for a process"
                },
                {
                    "name": "SeBackupPrivilege",
                    "state": "danger",
                    "machineguid": "",
                    "importance": "Low",
                    "standardvalue": ["*S-1-5-32-544"],
                    "actualvalue": "",
                    "comparemethod": "equal",
                    "description": "Back up files and directories"
                },
                {
                    "name": "SeTimeZonePrivilege",
                    "state": "danger",
                    "machineguid": "",
                    "importance": "Low",
                    "standardvalue": ["*S-1-5-32-544", "*S-1-5-19"],
                    "actualvalue": "",
                    "comparemethod": "equal",
                    "description": "Change the time zone"
                },
                {
                    "name": "SeCreatePagefilePrivilege",
                    "state": "danger",
                    "machineguid": "",
                    "importance": "Low",
                    "standardvalue": ["*S-1-5-32-544"],
                    "actualvalue": "",
                    "comparemethod": "equal",
                    "description": "Create a pagefile"
                },
                {
                    "name": "SeCreateTokenPrivilege",
                    "state": "danger",
                    "machineguid": "",
                    "importance": "Low",
                    "standardvalue": "",
                    "actualvalue": "",
                    "comparemethod": "equal",
                    "description": "Create a token object"
                },
                {
                    "name": "SeCreatePermanentPrivilege",
                    "state": "danger",
                    "machineguid": "",
                    "importance": "Low",
                    "standardvalue": "",
                    "actualvalue": "",
                    "comparemethod": "equal",
                    "description": "Create permanent shared objects"
                },
                {
                    "name": "SeCreateSymbolicLinkPrivilege",
                    "state": "danger",
                    "machineguid": "",
                    "importance": "Low",
                    "standardvalue": ["*S-1-5-32-544"],
                    "actualvalue": "",
                    "comparemethod": "equal",
                    "description": "Create symbolic links"
                }
            ]
        }

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

    def is_admin(self):
        try:
            return ctypes.windll.shell32.IsUserAnAdmin()
        except:
            return False



    def getStandardValue(self):
        cmd = r"secedit /export /cfg C:\account.ini"
        if self.is_admin():
            # print("管理员权限")
            self.run_code(cmd)
            # 将要运行的代码加到这里
        else:
            # print("提权")
            ctypes.windll.shell32.ShellExecuteW(None, "runas", sys.executable, __file__, None, 0)

        # 类实例化
        # self.run_code(r"secedit /export /cfg C:\Windows\Temp\account.ini")
        path = r'C:\account.ini'
        # config = configparser.ConfigParser(allow_no_value=True)
        config = NewConfigParser()
        config.read(path, encoding='utf-16')
        # print(config.options("System Access"))

        # 账户锁定时间
        if (config.has_option("System Access", "ResetLockoutCount")):
            self.RLCValue = int(config.get("System Access", "ResetLockoutCount"))
            self.ResetLockoutCount()
        # 账户锁定阈值
        if (config.has_option("System Access", "LockoutBadCount")):
            self.LBCValue = int(config.get("System Access", "LockoutBadCount"))
            self.LockoutBadCount()
        # 复位账户锁定计数器
        if (config.has_option("System Access", "LockoutDuration")):
            self.LDValue = int(config.get("System Access", "LockoutDuration"))
            self.LockoutDuration()
        # 历史密码数量
        if (config.has_option("System Access", "PasswordHistorySize")):
            self.RHSValue = int(config.get("System Access", "PasswordHistorySize"))
            self.PasswordHistorySize()
        # 密码过期时间
        if (config.has_option("System Access", "MaximumPasswordAge")):
            self.MPAValue = int(config.get("System Access", "MaximumPasswordAge"))
            self.MaximumPasswordAge()
        # 网络登录账户
        if (config.has_option("Privilege Rights", "SeNetworkLogonRight")):
            self.NLRight = config.get("Privilege Rights", "SeNetworkLogonRight").split(',')
            self.SeNetworkLogonRight()
        # 系统时间更改账户
        if (config.has_option("Privilege Rights", "SeSystemtimePrivilege")):
            self.SPrivilege = config.get("Privilege Rights", "SeSystemtimePrivilege").split(',')
            self.SeSystemtimePrivilege()
        # 将工作站添加到域中
        if (config.has_option("Privilege Rights", "SeManageVolumePrivilege")):
            self.SMVPrivilege = config.get("Privilege Rights", "SeManageVolumePrivilege").split(',')
            self.SeManageVolumePrivilege()
        # 调整进程可用最大内存量
        if (config.has_option("Privilege Rights", "SeIncreaseQuotaPrivilege")):
            self.SIQPrivilege = config.get("Privilege Rights", "SeIncreaseQuotaPrivilege").split(',')
            self.SeIncreaseQuotaPrivilege()
        # 绕过文件和目录权限备份系统
        if (config.has_option("Privilege Rights", "SeBackupPrivilege")):
            self.SBPrivilege = config.get("Privilege Rights", "SeBackupPrivilege").split(',')
            self.SeBackupPrivilege()
        # 更改计算机时区
        if (config.has_option("Privilege Rights", "SeTimeZonePrivilege")):
            self.STZPrivilege = config.get("Privilege Rights", "SeTimeZonePrivilege").split(',')
            self.SeTimeZonePrivilege()
        # 更改页面文件的大小
        if (config.has_option("Privilege Rights", "SeCreatePagefilePrivilege")):
            self.SCPPrivilege = config.get("Privilege Rights", "SeCreatePagefilePrivilege").split(',')
            self.SeCreatePagefilePrivilege()
        # 允许进程创建访问令牌
        if (config.has_option("Privilege Rights", "SeCreateTokenPrivilege")):
            self.SCTPrivilege = config.get("Privilege Rights", "SeCreateTokenPrivilege").split(',')
            self.SeCreateTokenPrivilege()
        # 创建永久共享对象
        if (config.has_option("Privilege Rights", "SeCreatePermanentPrivilege")):
            self.SCPermanentPrivilege = config.get("Privilege Rights", "SeCreatePermanentPrivilege").split(',')
            self.SeCreatePermanentPrivilege()
        # 创建符号链接
        if (config.has_option("Privilege Rights", "SeCreateSymbolicLinkPrivilege")):
            self.SCSLPrivilege = config.get("Privilege Rights", "SeCreateSymbolicLinkPrivilege").split(',')
            self.SeCreateSymbolicLinkPrivilege()

    def compare(self, com_dict, ActualValue):
        com_dict["actualvalue"] = ActualValue
        com_dict["machineguid"] = self.getMachineGuid()
        if com_dict["comparemethod"] == "more":
            if ActualValue >= com_dict["standardvalue"]:
                com_dict["state"] = "safe"
            else:
                com_dict["state"] = "danger"
        elif com_dict["comparemethod"] == "less":
            if ActualValue < com_dict["standardvalue"]:
                com_dict["state"] = "safe"
            else:
                com_dict["state"] = "danger"
        elif com_dict["comparemethod"] == "between":
            standar = ast.literal_eval(com_dict["standardvalue"])
            if ActualValue > standar[0] and ActualValue < standar[1]:
                com_dict["state"] = "safe"
            else:
                com_dict["state"] = "danger"
        elif com_dict["comparemethod"] == "equal":
            if ActualValue == com_dict["standardvalue"]:
                com_dict["state"] = "safe"
            else:
                com_dict["state"] = "danger"
        return com_dict

    def ResetLockoutCount(self):
        ResetLockoutCount = self.sql["account"][0]
        self.sql["account"][0] = self.compare(
            ResetLockoutCount,
            self.RLCValue
        )

    def LockoutBadCount(self):
        LockoutBadCount = self.sql["account"][1]
        self.sql["account"][1] = self.compare(
            LockoutBadCount,
            self.LBCValue
        )

    def LockoutDuration(self):
        LockoutDuration = self.sql["account"][2]
        self.sql["account"][2] = self.compare(
            LockoutDuration,
            self.LDValue
        )

    def PasswordHistorySize(self):
        PasswordHistorySize = self.sql["account"][3]
        self.sql["account"][3] = self.compare(
            PasswordHistorySize,
            self.RHSValue
        )

    def MaximumPasswordAge(self):
        MaximumPasswordAge = self.sql["account"][4]
        self.sql["account"][4] = self.compare(
            MaximumPasswordAge,
            self.MPAValue
        )

    def SeNetworkLogonRight(self):
        self.sql["account"][5]["actualvalue"] = self.NLRight
        self.sql["account"][5]["state"] = "safe"
        for network in self.NLRight:
            if network in self.sql["account"][5]["standardvalue"]:
                continue
            else:
                self.sql["account"][5]["state"] = "danger"
                self.sql["account"][5]["description"] = "danger: " + str(network)

    def SeSystemtimePrivilege(self):
        self.sql["account"][6]["actualvalue"] = self.SPrivilege
        self.sql["account"][6]["state"] = "safe"
        for network in self.SPrivilege:
            if network in self.sql["account"][6]["standardvalue"]:
                continue
            else:
                self.sql["account"][6]["state"] = "danger"
                self.sql["account"][6]["description"] = "danger: " + str(network)

    def SeManageVolumePrivilege(self):
        self.sql["account"][7]["actualvalue"] = self.SMVPrivilege
        self.sql["account"][7]["state"] = "safe"
        for network in self.SMVPrivilege:
            if network in self.sql["account"][7]["standardvalue"]:
                continue
            else:
                self.sql["account"][7]["state"] = "danger"
                self.sql["account"][7]["description"] = "danger: " + str(network)

    def SeIncreaseQuotaPrivilege(self):
        self.sql["account"][8]["actualvalue"] = self.SIQPrivilege
        self.sql["account"][8]["state"] = "safe"
        for network in self.SIQPrivilege:
            if network in self.sql["account"][8]["standardvalue"]:
                continue
            else:
                self.sql["account"][8]["state"] = "danger"
                self.sql["account"][8]["description"] = "danger: " + str(network)

    def SeBackupPrivilege(self):
        self.sql["account"][9]["actualvalue"] = self.SBPrivilege
        self.sql["account"][9]["state"] = "safe"
        for network in self.SBPrivilege:
            if network in self.sql["account"][9]["standardvalue"]:
                continue
            else:
                self.sql["account"][9]["state"] = "danger"
                self.sql["account"][9]["description"] = "danger: " + str(network)

    def SeTimeZonePrivilege(self):
        self.sql["account"][10]["actualvalue"] = self.STZPrivilege
        self.sql["account"][10]["state"] = "safe"
        for network in self.STZPrivilege:
            if network in self.sql["account"][10]["standardvalue"]:
                continue
            else:
                self.sql["account"][10]["state"] = "danger"
                self.sql["account"][10]["description"] = "danger: " + str(network)

    def SeCreatePagefilePrivilege(self):
        self.sql["account"][11]["actualvalue"] = self.SCPPrivilege
        self.sql["account"][11]["state"] = "safe"
        for network in self.SCPPrivilege:
            if network in self.sql["account"][11]["standardvalue"]:
                continue
            else:
                self.sql["account"][11]["state"] = "danger"
                self.sql["account"][11]["description"] = "danger: " + str(network)

    def SeCreateTokenPrivilege(self):
        self.sql["account"][12]["actualvalue"] = self.SCTPrivilege
        self.sql["account"][12]["state"] = "safe"
        for network in self.SCTPrivilege:
            if network in self.sql["account"][12]["standardvalue"]:
                continue
            else:
                self.sql["account"][12]["state"] = "danger"
                self.sql["account"][12]["description"] = "danger: " + str(network)

    def SeCreatePermanentPrivilege(self):
        self.sql["account"][13]["actualvalue"] = self.SCPermanentPrivilege
        self.sql["account"][13]["state"] = "safe"
        for network in self.SCPermanentPrivilege:
            if network in self.sql["account"][13]["standardvalue"]:
                continue
            else:
                self.sql["account"][13]["state"] = "danger"
                self.sql["account"][13]["description"] = "danger: " + str(network)

    def SeCreateSymbolicLinkPrivilege(self):
        self.sql["account"][14]["actualvalue"] = self.SCSLPrivilege
        self.sql["account"][14]["state"] = "safe"
        for network in self.SCSLPrivilege:
            if network in self.sql["account"][14]["standardvalue"]:
                continue
            else:
                self.sql["account"][14]["state"] = "danger"
                self.sql["account"][14]["description"] = "danger: " + str(network)

    def lmain(self):
        for i in self.sql["account"]:
            i["machineguid"] = self.getMachineGuid()
        self.getStandardValue()

        # self.ResetLockoutCount()
        # self.LockoutBadCount()
        # self.LockoutDuration()
        # self.PasswordHistorySize()
        # self.MaximumPasswordAge()
        # self.SeNetworkLogonRight()
        # self.SeSystemtimePrivilege()
        # self.SeManageVolumePrivilege()
        # self.SeIncreaseQuotaPrivilege()
        # self.SeBackupPrivilege()
        # self.SeTimeZonePrivilege()
        # self.SeCreatePagefilePrivilege()
        # self.SeCreateTokenPrivilege()
        # self.SeCreatePermanentPrivilege()
        # self.SeCreateSymbolicLinkPrivilege()


if __name__ == '__main__':
    a = Baseline()
    a.lmain()
    print(json.dumps(a.sql))
