#! python
# encoding: utf-8
import json
import uuid
import subprocess


class LinuxBaseline:
    def __init__(self):
        self.sql = {
            "account": [
                {
                    "name": "SSHPermissions",
                    "state": "",
                    "machineguid": "",
                    "importance": "High",
                    "standardvalue": "-rw-------",
                    "actualvalue": "",
                    "comparemethod": "equal",
                    "description": "permissions on /etc/ssh/sshd_config"
                },
                {
                    "name": "SSHProtocol",
                    "state": "",
                    "machineguid": "",
                    "importance": "High",
                    "standardvalue": "Protocol 2",
                    "actualvalue": "",
                    "comparemethod": "equal",
                    "description": "Ensure SSH Protocol is set to 2"
                },
                {
                    "name": "SSHLogLevel",
                    "state": "",
                    "machineguid": "",
                    "importance": "High",
                    "standardvalue": "LogLevel INFO",
                    "actualvalue": "",
                    "comparemethod": "equal",
                    "description": "Ensure SSH LogLevel is set to INFO"
                },
                {
                    "name": "SSHX11forwarding",
                    "state": "",
                    "machineguid": "",
                    "importance": "High",
                    "standardvalue": "X11Forwarding no",
                    "actualvalue": "",
                    "comparemethod": "equal",
                    "description": "Ensure SSH X11 forwarding is disabled"
                },
                {
                    "name": "SSHMaxAuthTries",
                    "state": "",
                    "machineguid": "",
                    "importance": "Middle",
                    "standardvalue": "MaxAuthTries 4",
                    "actualvalue": "",
                    "comparemethod": "less",
                    "description": "Ensure SSH MaxAuthTries is set to 4 or less"
                }
                ,
                {
                    "name": "SSHWarningBanner",
                    "state": "",
                    "machineguid": "",
                    "importance": "Low",
                    "standardvalue": "Banner /etc/issue.net",
                    "actualvalue": "",
                    "comparemethod": "equal",
                    "description": "Ensure SSH warning banner is configured"
                }
                ,
                {
                    "name": "SSHLoginGraceTime",
                    "state": "",
                    "machineguid": "",
                    "importance": "Middle",
                    "standardvalue": "LoginGraceTime 60",
                    "actualvalue": "",
                    "comparemethod": "less",
                    "description": "Ensure SSH LoginGraceTime is set to one minute or less"
                }
                ,
                {
                    "name": "SSHPermitUserEnvironment",
                    "state": "",
                    "machineguid": "",
                    "importance": "High",
                    "standardvalue": "PermitUserEnvironment no",
                    "actualvalue": "",
                    "comparemethod": "equal",
                    "description": "Ensure SSH PermitUserEnvironment is disabled"
                }
                ,
                {
                    "name": "SSHPermitEmptyPasswords",
                    "state": "",
                    "machineguid": "",
                    "importance": "High",
                    "standardvalue": "PermitEmptyPasswords no",
                    "actualvalue": "",
                    "comparemethod": "equal",
                    "description": "Ensure SSH PermitEmptyPasswords is disabled"
                }
                ,
                {
                    "name": "SSHRootLogin",
                    "state": "",
                    "machineguid": "",
                    "importance": "High",
                    "standardvalue": "PermitRootLogin no",
                    "actualvalue": "",
                    "comparemethod": "equal",
                    "description": "Ensure SSH root login is disabled"
                }
                ,
                {
                    "name": "SSHHostbasedAuthentication",
                    "state": "",
                    "machineguid": "",
                    "importance": "High",
                    "standardvalue": "HostbasedAuthentication no",
                    "actualvalue": "",
                    "comparemethod": "equal",
                    "description": "Ensure SSH HostbasedAuthentication is disabled"
                }
                ,
                {
                    "name": "SSHIgnoreRhosts",
                    "state": "",
                    "machineguid": "",
                    "importance": "Middle",
                    "standardvalue": "IgnoreRhosts yes",
                    "actualvalue": "",
                    "comparemethod": "equal",
                    "description": "Ensure SSH IgnoreRhosts is enabled"
                }
            ]
        }

    def get_mac_address(self):
        node = uuid.getnode()
        mac = uuid.UUID(int=node).hex[-12:]
        for i in range(12):
            self.sql['account'][i]['machineguid'] = mac

    # 获取命令运行结果
    def run_code(self, cmd):
        res = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
        for line in res.communicate():
            return (line.decode("UTF-8"))

    def SSHPermissions(self):
        cmd1 = r"ls -l /etc/ssh/sshd_config | awk '{print $1}'"
        responce = self.run_code(cmd1)
        self.sql['account'][0]['actualvalue'] = responce.strip('\n')
        if responce == self.sql['account'][0]['standardvalue']:
            self.sql['account'][0]['state'] = "safe"
        else:
            self.sql['account'][0]['state'] = "danger"

    def SSHProtocol(self):
        cmd2 = r'grep "Protocol" /etc/ssh/sshd_config'
        responce = self.run_code(cmd2)
        self.sql['account'][1]['actualvalue'] = responce.strip('\n')
        if responce == self.sql['account'][1]['standardvalue']:
            self.sql['account'][1]['state'] = "safe"
        else:
            self.sql['account'][1]['state'] = "danger"

    def SSHLogLevel(self):
        cmd3 = 'grep "LogLevel" /etc/ssh/sshd_config'
        responce = self.run_code(cmd3)
        self.sql['account'][2]['actualvalue'] = responce.strip('\n')
        if responce == self.sql['account'][2]['standardvalue']:
            self.sql['account'][2]['state'] = "safe"
        else:
            self.sql['account'][2]['state'] = "danger"

    def SSHX11forwarding(self):
        cmd4 = 'grep "^X11Forwarding" /etc/ssh/sshd_config'
        responce = self.run_code(cmd4)
        self.sql['account'][3]['actualvalue'] = responce.strip('\n')
        if responce == self.sql['account'][3]['standardvalue']:
            self.sql['account'][3]['state'] = "safe"
        else:
            self.sql['account'][3]['state'] = "danger"

    def SSHMaxAuthTries(self):
        cmd5 = 'grep "MaxAuthTries" /etc/ssh/sshd_config'
        responce = self.run_code(cmd5)
        self.sql['account'][4]['actualvalue'] = responce.strip('\n')
        if responce == self.sql['account'][4]['standardvalue']:
            self.sql['account'][4]['state'] = "safe"
        else:
            self.sql['account'][4]['state'] = "danger"

    def SSHWarningBanner(self):
        cmd6 = 'grep "Banner" /etc/ssh/sshd_config'
        responce = self.run_code(cmd6)
        self.sql['account'][5]['actualvalue'] = responce.strip('\n')
        if responce == self.sql['account'][5]['standardvalue']:
            self.sql['account'][5]['state'] = "safe"
        else:
            self.sql['account'][5]['state'] = "danger"

    def SSHLoginGraceTime(self):
        cmd7 = 'grep "LoginGraceTime" /etc/ssh/sshd_config'
        responce = self.run_code(cmd7)
        self.sql['account'][6]['actualvalue'] = responce.strip('\n')
        if responce == self.sql['account'][6]['standardvalue']:
            self.sql['account'][6]['state'] = "safe"
        else:
            self.sql['account'][6]['state'] = "danger"

    def SSHPermitUserEnvironment(self):
        cmd8 = 'grep "LoginGraceTime" /etc/ssh/sshd_config'
        responce = self.run_code(cmd8)
        self.sql['account'][7]['actualvalue'] = responce.strip('\n')
        if responce == self.sql['account'][7]['standardvalue']:
            self.sql['account'][7]['state'] = "safe"
        else:
            self.sql['account'][7]['state'] = "danger"

    def SSHPermitEmptyPasswords(self):
        cmd9 = 'grep "PermitEmptyPasswords" /etc/ssh/sshd_config'
        responce = self.run_code(cmd9)
        self.sql['account'][8]['actualvalue'] = responce.strip('\n')
        if responce == self.sql['account'][8]['standardvalue']:
            self.sql['account'][8]['state'] = "safe"
        else:
            self.sql['account'][8]['state'] = "danger"

    def SSHRootLogin(self):
        cmd10 = "grep '[^\"]PermitRootLogin' /etc/ssh/sshd_config"
        responce = self.run_code(cmd10)
        self.sql['account'][9]['actualvalue'] = responce.strip('\n')
        if responce == self.sql['account'][9]['standardvalue']:
            self.sql['account'][9]['state'] = "safe"
        else:
            self.sql['account'][9]['state'] = "danger"

    def SSHHostbasedAuthentication(self):
        cmd11 = 'grep "[^ ]HostbasedAuthentication" /etc/ssh/sshd_config'
        responce = self.run_code(cmd11)
        self.sql['account'][10]['actualvalue'] = responce.strip('\n')
        if responce == self.sql['account'][10]['standardvalue']:
            self.sql['account'][10]['state'] = "safe"
        else:
            self.sql['account'][10]['state'] = "danger"

    def SSHIgnoreRhosts(self):
        cmd12 = 'grep "IgnoreRhosts" /etc/ssh/sshd_config'
        responce = self.run_code(cmd12)
        self.sql['account'][11]['actualvalue'] = responce.strip('\n')
        if responce == self.sql['account'][11]['standardvalue']:
            self.sql['account'][11]['state'] = "safe"
        else:
            self.sql['account'][11]['state'] = "danger"

    def main(self):
        self.get_mac_address()
        self.SSHPermissions()
        self.SSHProtocol()
        self.SSHLogLevel()
        self.SSHX11forwarding()
        self.SSHMaxAuthTries()
        self.SSHWarningBanner()
        self.SSHLoginGraceTime()
        self.SSHPermitUserEnvironment()
        self.SSHPermitEmptyPasswords()
        self.SSHRootLogin()
        self.SSHHostbasedAuthentication()
        self.SSHIgnoreRhosts()


if __name__ == '__main__':
    l = LinuxBaseline()
    l.main()
    print(json.dumps(l.sql))
