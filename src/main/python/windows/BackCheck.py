import os
import subprocess
import winreg
import json

class BackCheck:


    def get_trueuser(self):
        cmd = r"net user >user.txt"

        listAutoRuns = []
        os.system(cmd)

        with open("user.txt", "r") as f:
            result = f.readlines()
        for line in result:
            if "-------" in line:
                index_left = result.index(line)
            if "命令成功完成" in line:
                index_right = result.index(line)
                break
        for line in result[index_left + 1:index_right]:
            for i in line.split(' '):
                if i != '' and i != '\n':
                    listAutoRuns.append(i)
        # print(listAutoRuns)
        return listAutoRuns

    def get_shadowuser(self):
        root1 = winreg.ConnectRegistry(None, winreg.HKEY_LOCAL_MACHINE)
        result = []
        try:
            # 2. 指定想要访问的子健
            reg_path = r"SAM\SAM\Domains\Account\Users\Names"
            # print("--->开始读取Users Names...")
            # 3. 打开相应子健
            try:
                key1 = winreg.OpenKey(root1, reg_path)  # 打开localmachine的autorun列表
            except PermissionError:
                return None
            try:
                count = 0
                while (1):
                    try:
                        # 4. 通过winreg对象的EnumKey()方法迭代其中的键值
                        user = winreg.EnumKey(key1, count)
                        # result[n] = v
                        result.append(user)
                        count += 1
                    except EnvironmentError:
                        break
            finally:
                # 5. 关闭相应子健
                winreg.CloseKey(key1)
        finally:
            # 6. 关闭相应根键连接
            winreg.CloseKey(root1)
        return result

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

    def get_BackCheck(self):
        back_dict = {}
        back_dict['MachineGuid'] = self.getMachineGuid()
        back_dict['detect'] = False
        back_dict['shadowuser'] = ""
        a = []
        if self.get_shadowuser() != None:
            if self.get_trueuser() != self.get_shadowuser():
                back_dict['detect'] = True
                for i in self.get_shadowuser():
                    if i not in self.get_trueuser():
                        a.append(i)
                back_dict['shadowuser'] = ','.join(a)
        return json.dumps(back_dict)


if __name__ == '__main__':
    b = BackCheck()
    print(b.get_BackCheck())

