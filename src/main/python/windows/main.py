import subprocess
import time
import winreg

from AccountInfo import AccountBaseline
from HardwareInfo import HardwareBaseline
from ScanningBasics import ScanningBasics
from ServiceInfo import ServiceBaseline
from SystemInfo import SystemBaseline
from Baseline import Baseline
from BackCheck import BackCheck
import requests
import json


class Getjson:
    def get_SystemBaseline_json(self):
        system = SystemBaseline()
        return system.get_SystemBaseline_json()

    def get_ScanningBasics_json(self):
        scan = ScanningBasics()
        return scan.get_ScanningBasics_json()

    def get_AccountBaseline_json(self):
        account = AccountBaseline()
        return account.get_AccountBaseline_json()

    def get_HardwareBaseline_json(self):
        hardware = HardwareBaseline()
        return hardware.get_HardwareBaseline_json()

    def get_ServiceBaseline_json(self, count):
        service = ServiceBaseline()
        return service.get_ServiceBaseline_json(count)

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

    def get_Baseline_json(self):
        baseline = Baseline()
        baseline.lmain()
        return json.dumps(baseline.sql)

    def get_BackCheck_json(self):
        backcheck = BackCheck()
        return backcheck.get_BackCheck()


class PostJson:
    def postHttpRequestData(self, username, password):
        req = requests.post('http://localhost:8080/user/insert',
                            headers={'Content-Type': 'application/json;charset=utf-8'},
                            data=json.dumps({
                                'username': username,
                                'password': password
                            }))
        jsonObject = req.json()  # 接收返回的json数据
        print(jsonObject)  # 返回字节形式
        print(jsonObject['msg'])  # json属性获取
        dataJson = jsonObject['data']
        print(dataJson['role'])

    def postJson(self, json_file):
        req = requests.post('http://localhost:8080/user/jsonUpload',
                            headers={'Content-Type': 'application/json;charset=utf-8'},
                            data=json_file
                            )
        data = req.json()  # 接收返回的json数据
        print(data)  # 返回字节形式
        # print(data['msg'])  # json属性获取

    def postHttpRequestParam(self, id):
        req = requests.post('http://localhost:8080/user/deleteById',
                            params={'id': id})
        data = req.json()  # 接收返回的json数据
        print(data)  # 返回字节形式
        # print(data['msg'])  # json属性获取

    def postHardwareBaseline(self, json_file):
        req = requests.post('http://82.156.7.201/receive/hardwareBaseline',
                            headers={'Content-Type': 'application/json;charset=utf-8'},
                            data=json_file
                            )
        data = req.json()  # 接收返回的json数据
        print(data)  # 返回字节形式
        # print(data['msg'])  # json属性获取

    def postAccountBaseline(self, json_file):
        req = requests.post('http://82.156.7.201/receive/accountBaseline',
                            headers={'Content-Type': 'application/json;charset=utf-8'},
                            data=json_file
                            )
        data = req.json()  # 接收返回的json数据
        print(data)  # 返回字节形式
        # print(data['msg'])  # json属性获取

    def postSystemBaseline(self, json_file):
        req = requests.post('http://82.156.7.201/receive/systemBaseline',
                            headers={'Content-Type': 'application/json;charset=utf-8'},
                            data=json_file
                            )
        data = req.json()  # 接收返回的json数据
        print(data)  # 返回字节形式
        # print(data['msg'])  # json属性获取

    def postServiceBaseline(self, json_file):
        req = requests.post('http://localhost:80/user/ServiceBaseline',
                            headers={'Content-Type': 'application/json;charset=utf-8'},
                            data=json_file
                            )
        data = req.json()  # 接收返回的json数据
        print(data)  # 返回字节形式
        # print(data['msg'])  # json属性获取

    def postBaseline(self, json_file):
        req = requests.post('http://82.156.7.201/receive/regeditBaseline',
                            headers={'Content-Type': 'application/json;charset=utf-8'},
                            data=json_file
                            )
        data = req.json()  # 接收返回的json数据
        print(data)  # 返回字节形式

    def postBackCheck(self, json_file):
        req = requests.post('http://82.156.7.201/receive/shadowBaseline',
                            headers={'Content-Type': 'application/json;charset=utf-8'},
                            data=json_file
                            )
        data = req.json()  # 接收返回的json数据
        print(data)  # 返回字节形式


if __name__ == '__main__':
    data = Getjson()
    count = 1
    post = PostJson()
    MachineGuid = data.getMachineGuid()

    print('-' * 20 + "欢迎来到BCVS基线检测系统" + '-' * 20)

    try:
        print("上传硬件信息...")
        post.postHardwareBaseline(data.get_HardwareBaseline_json())
        print("上传账户信息...")
        post.postAccountBaseline(data.get_AccountBaseline_json())
        print("上传系统信息...")
        post.postSystemBaseline(data.get_SystemBaseline_json())
        print("上传基线检测信息...")
        post.postBaseline(data.get_Baseline_json())
        print("上传后门检测信息...")
        post.postBackCheck(data.get_BackCheck_json())

        print("MachineGuid是唯一标识符, 用于绑定设备, 请复制此值! ! !")
        print('MachineGuid:', MachineGuid)
        input('*分钟后会重发检测信息, 或手动关闭命令行窗口...')

        while True:
            time.sleep(60)
            print("上传基线检测信息...")
            post.postBaseline(data.get_Baseline_json())
            print("上传后门检测信息...")
            post.postBackCheck(data.get_BackCheck_json())
            count = count + 1
    except:
        print('连接失败, 稍后重试!')
        input('Press Enter to exit…')
