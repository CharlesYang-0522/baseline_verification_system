from linux_host import *
from linux_account import *
from linux_Baseline import *
from linux_hardware import *
from linux_back import *
import requests
import json


class Getjson:
    def get_SystemBaseline_json(self):
        system = LinuxHost()
        return system.get_linux_host()

    def get_AccountBaseline_json(self):
        account = LinuxAccount()
        return account.get_linux_account()

    def get_HardwareBaseline_json(self):
        hardware = LinuxHardware()
        return hardware.get_linux_hardware()

    def getMachineGuid(self):
        node = uuid.getnode()
        mac = uuid.UUID(int=node).hex[-12:]
        return mac

    def run_code(self, cmd):
        res = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
        for line in res.communicate():
            return (line)

    def get_Baseline_json(self):
        baseline = LinuxBaseline()
        baseline.main()
        return json.dumps(baseline.sql)

    def get_Back_json(self):
        back = LinuxBack()
        return back.get_BackCheck()


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
    # print("这是一个弹出提示框")
    data = Getjson()
    count = 1
    post = PostJson()
    MachineGuid = data.getMachineGuid()

    print('-' * 20 + "欢迎来到老王后宫的检测系统" + '-' * 20)

    try:
        print("MachineGuid是唯一标识符, 用于绑定设备, 请复制此值! ! !")
        print('MachineGuid:', MachineGuid)
        input('Press Enter to exit…')
        print("上传硬件信息...")
        post.postHardwareBaseline(data.get_HardwareBaseline_json())
        print("上传账户信息...")
        post.postAccountBaseline(data.get_AccountBaseline_json())
        print("上传系统信息...")
        post.postSystemBaseline(data.get_SystemBaseline_json())
        print("上传基线检测信息...")
        post.postBaseline(data.get_Baseline_json())
        print("上传后门检测信息...")
        post.postBackCheck(data.get_Back_json())
    except:
        print('连接失败, 稍后重试!')
        input('Press Enter to exit…')
