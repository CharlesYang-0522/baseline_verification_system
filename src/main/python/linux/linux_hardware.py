#!/usr/bin/python
# -*- coding:utf-8 -*-
from __future__ import print_function

import subprocess
import uuid
from subprocess import Popen, PIPE
from collections import OrderedDict
import os
import json

information = {}
processor = []


class LinuxHardware:

    def meminfo(self):
        ''' Return the information in /proc/meminfo
        as a dictionary '''
        meminfo = OrderedDict()

        with open('/proc/meminfo') as f:
            for line in f:
                meminfo[line.split(':')[0]] = line.split(':')[1].strip()
        information["info_physical_memory"] = str(round(int(meminfo['MemTotal'][:-3]) / 1024 / 1024, 2)) + 'G'

    def cpuinfo(self):
        with open('/proc/cpuinfo') as f:
            for line in f:
                # Ignore the blank line separating the information between
                # details about two processing units
                if line.strip():
                    if line.rstrip('\n').startswith('processor'):
                        pid = line.rstrip('\n').split(':')[1]
                        processor.append(pid)

                    if line.rstrip('\n').startswith('model name'):
                        model_name = line.rstrip('\n').split(':')[1]
                        information["info_cpu_name"] = model_name

                    if line.rstrip('\n').startswith('cache size'):
                        cacheSize = line.rstrip('\n').split(':')[1]
                        information["info_l3_cache"] = cacheSize

                    if line.rstrip('\n').startswith('clflush size'):
                        dataWidth = line.rstrip('\n').split(':')[1]
                        information["info_data_width"] = dataWidth
            information["info_number_core"] = len(processor)

    def getDmi(self):
        p = Popen(['dmidecode'], stdout=PIPE)
        data = p.stdout.read()
        return data

    def parseDmi(self, data):
        lines = []
        dmi_list = []
        line_in = False
        dmi_list = [i for i in data.decode().split('\n') if i]
        for line in dmi_list:
            if line.startswith('System Information'):
                line_in = True
                continue
            if line_in:
                if not line[0].strip():
                    lines.append(line)
                else:
                    break
        return lines

    def dmiDic(self):
        dmi_dic = {}
        data = self.getDmi()
        lines = self.parseDmi(data)
        dic = dict([i.strip().split(': ') for i in lines])
        # information["manufacturer"] = dic["Manufacturer"]
        # information["Product Name"] = dic["Product Name"]
        # information["version"] = dic["Version"]
        information["info_SerialNumber"] = dic["Serial Number"]

    def getMachineGuid(self):
        node = uuid.getnode()
        mac = uuid.UUID(int=node).hex[-12:]
        return mac

    def run_code(self, cmd):
        res = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
        for line in res.communicate():
            return (line.decode("UTF-8"))

    def getosversion(self):
        cmd = "dpkg --print-architecture"
        return self.run_code(cmd)

    def hostname(self):
        p = Popen(['hostnamectl'], stdout=PIPE)
        data = p.stdout.read()
        lines = []
        lines = [i for i in data.decode().split('\n') if i]
        # print(lines)
        dic = dict([i.strip().split(': ') for i in lines])
        information['info_os_architecture'] = self.getosversion()
        information["info_fullname"] = dic["Static hostname"]
        information["info_os"] = dic["Operating System"]
        information["info_systemtype"] = dic["Architecture"]
        information["info_mainboard"] = dic["Hardware Vendor"]
        information["info_board_model"] = dic["Hardware Model"]
        information["MachineGuid"] = self.getMachineGuid()

    def language(self):
        data = os.popen("echo $LANG")
        information["info_mu_languages"] = data.read().split('.')[0]

    def get_linux_hardware(self):
        self.cpuinfo()
        self.meminfo()
        self.dmiDic()
        self.hostname()
        self.language()
        return json.dumps(information)


if __name__ == '__main__':
    l = LinuxHardware()
    print(l.get_linux_hardware())
