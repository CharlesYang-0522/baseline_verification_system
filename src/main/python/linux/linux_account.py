import subprocess
import json
import uuid


class LinuxAccount:
    def __init__(self):
        self.a = 0

    def run_code(self, cmd):
        res = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
        for line in res.communicate():
            return (line.decode("UTF-8"))

    def get_mac_address(self):
        node = uuid.getnode()
        mac = uuid.UUID(int=node).hex[-12:]
        return mac

    def user(self):
        result = {}
        result["MachineGuid"] = self.get_mac_address()
        result["type"] = ""
        result["describe"] = ""
        cmd = r"cat /etc/passwd | awk -F ':' '$3>500||$3==0 {print $1,$3,$6,$7}'"
        responce = self.run_code(cmd)
        account_list = []
        responce = responce.split('\n')
        for line in responce:
            new = {}
            if line == '':
                break
            x = line.split(" ")
            new["Caption"] = self.get_mac_address() + r"\\" +x[0]
            new["Description"] = "Table:" + x[2] + ", Shell:" + x[3]
            new["Domain"] = "Ubuntu server"
            new["Name"] = x[0]
            new["PasswordRequired"] = "true"
            new["SID"] = x[1]
            new["Status"] = "OK"
            account_list.append(new)
        result["user"] = account_list
        return result

    def get_linux_account(self):
        return json.dumps(self.user())


if __name__ == '__main__':
    l = LinuxAccount()
    print(json.dumps(l.user()))
