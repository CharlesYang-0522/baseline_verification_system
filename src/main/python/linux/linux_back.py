import subprocess
import uuid
import json


class LinuxBack:

    def run_code(self, cmd):
        res = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
        for line in res.communicate():
            return (line.decode("UTF-8"))

    def get_mac_address(self):
        node = uuid.getnode()
        mac = uuid.UUID(int=node).hex[-12:]
        return mac

    def check(self):
        back_list = []
        cmd = r"cat /etc/passwd | awk -F ':' '$3==0 {print $1,$3,$6}'"
        responce = self.run_code(cmd)
        responce = responce.split('\n')
        for line in responce:
            if line == '':
                break
            line = line.split(" ")
            if line[1] == '0' and line[2] != '/root':
                back_list.append(line[0])
        return back_list

    def get_BackCheck(self):
        back_dict = {}
        back_dict['MachineGuid'] = self.get_mac_address()
        back_dict['shadowuser'] = ""
        a = []
        if self.check():
            for i in self.check():
                a.append(i)
            back_dict['shadowuser'] = ','.join(a)
        return json.dumps(back_dict)


if __name__ == '__main__':
    l = LinuxBack()
    print(l.get_BackCheck())
