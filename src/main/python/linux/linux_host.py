#!/usr/bin/env python
# encoding: utf-8

import json
import subprocess
import uuid
import platform
import psutil
from socket import *
from subprocess import Popen, PIPE

class LinuxHost:

    def run_code(self, cmd):
        res = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
        for line in res.communicate():
            return (line.decode("UTF-8"))

    def get_OSCaption(self):
        OSCaption = platform.system()
        return OSCaption

    def get_OSVersion(self):
        OSVersion = platform.version()
        return OSVersion

    def get_mac_address(self):
        node = uuid.getnode()
        mac = uuid.UUID(int=node).hex[-12:]
        return mac

    def get_DiskCaption(self):
        DiskCaption = psutil.disk_partitions()[0].device
        return DiskCaption.split('/')[-1]

    def get_InterfaceType(self):
        InterfaceType = psutil.disk_partitions()[0].fstype
        return InterfaceType

    def get_IPAddress(self):
        cmd = "ip address | grep ens | awk '{print$2}'"
        IPAddress = self.run_code(cmd)
        return IPAddress.split('\n')[-2]

    def getMachineGuid(self):
        node = uuid.getnode()
        mac = uuid.UUID(int=node).hex[-12:]
        return mac

    def get_linux_host(self):
        result = {}

        p = Popen(['hostnamectl'], stdout=PIPE)
        data = p.stdout.read()
        lines = [i for i in data.decode().split('\n') if i]
        dic = dict([i.strip().split(': ') for i in lines])
        result["OSVersion"] = dic["Operating System"]
        result['MachineGuid'] = self.getMachineGuid()
        result['OSCaption'] = self.get_OSCaption()
        result["DiskCaption"] = self.get_DiskCaption()
        result["InterfaceType"] = self.get_InterfaceType()
        result["IPAddress"] = self.get_IPAddress()
        result["MACAddress"] = self.get_mac_address()
        return json.dumps(result)


if __name__ == '__main__':
    l = LinuxHost()
    print(l.get_linux_host())
