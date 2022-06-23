# Scanning_Basics
import platform
import subprocess
import uuid
import json

'''
    python中，platform模块给我们提供了很多方法去获取操作系统的信息
    如：
        import platform
        platform.platform()   #获取操作系统名称及版本号，'Windows-7-6.1.7601-SP1'
        platform.version()    #获取操作系统版本号，'6.1.7601'
        platform.architecture()   #获取操作系统的位数，('32bit', 'WindowsPE')
        platform.machine()    #计算机类型，'x86'
        platform.node()       #计算机的网络名称，'Natural-PC'
        platform.processor()  #计算机处理器信息，'x86 Family 16 Model 6 Stepping 3, AuthenticAMD'
        platform.uname()      #包含上面所有的信息汇总，uname_result(system='Windows', node='hongjie-PC',
                               release='7', version='6.1.7601', machine='x86', processor='x86 Family
                               16 Model 6 Stepping 3, AuthenticAMD')
        还可以获得计算机中python的一些信息：
        import platform
        platform.python_build()
        platform.python_compiler()
        platform.python_branch()
        platform.python_implementation()
        platform.python_revision()
        platform.python_version()
        platform.python_version_tuple()
'''

# global var
# 是否显示日志信息
SHOW_LOG = True


class ScanningBasics:

    def get_platform(self):
        '''获取操作系统名称及版本号'''
        return platform.platform()

    def get_version(self):
        '''获取操作系统版本号'''
        return platform.version()

    def get_architecture(self):
        '''获取操作系统的位数'''
        return platform.architecture()

    def get_machine(self):
        '''计算机类型'''
        return platform.machine()

    def get_node(self):
        '''计算机的网络名称'''
        return platform.node()

    def get_processor(self):
        '''计算机处理器信息'''
        return platform.processor()

    def get_system(self):
        '''获取操作系统类型'''
        return platform.system()

    def get_uname(self):
        '''汇总信息'''
        return platform.uname()

    def get_python_build(self):
        ''' the Python build number and date as strings'''
        return platform.python_build()

    def get_python_compiler(self):
        '''Returns a string identifying the compiler used for compiling Python'''
        return platform.python_compiler()

    def get_python_branch(self):
        '''Returns a string identifying the Python implementation SCM branch'''
        return platform.python_branch()

    def get_python_implementation(self):
        '''Returns a string identifying the Python implementation. Possible return values are: ‘CPython’, ‘IronPython’, ‘Jython’, ‘PyPy’.'''
        return platform.python_implementation()

    def get_python_version(self):
        '''Returns the Python version as string 'major.minor.patchlevel'
        '''
        return platform.python_version()

    def get_python_revision(self):
        '''Returns a string identifying the Python implementation SCM revision.'''
        return platform.python_revision()

    def get_python_version_tuple(self):
        '''Returns the Python version as tuple (major, minor, patchlevel) of strings'''
        return platform.python_version_tuple()

    def getMachineGuid(self):
        strCMD = "REG QUERY HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Cryptography"
        result_dict = {}
        strResult = self.run_code(strCMD)
        strResult = strResult.split('\n')
        for result in strResult:
            if result[0:4] == '    ':
                result = result.split('    ')
                result_dict[result[1]] = result[-1]
        return result_dict['MachineGuid']

    def run_code(self, cmd):
        res = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
        for line in res.communicate():
            return (line)

    def show_python_all_info(self):
        '''打印python的全部信息'''
        print('The Python build number and date as strings : [{}]'.format(self.get_python_build()))
        print('Returns a string identifying the compiler used for compiling Python : [{}]'.format(
            self.get_python_compiler()))
        print(
            'Returns a string identifying the Python implementation SCM branch : [{}]'.format(self.get_python_branch()))
        print('Returns a string identifying the Python implementation : [{}]'.format(self.get_python_implementation()))
        print('The version of Python ： [{}]'.format(self.get_python_version()))
        print('Python implementation SCM revision : [{}]'.format(self.get_python_revision()))
        print('Python version as tuple : [{}]'.format(self.get_python_version_tuple()))

    def json_python_info(self):
        m = {}
        m["python_build"] = self.get_python_build()
        m["python_compiler"] = self.get_python_compiler()
        m["python_branch"] = self.get_python_branch()
        m["python_implementation"] = self.get_python_implementation()
        m["python_version"] = self.get_python_version()
        m["python_revision"] = self.get_python_revision()
        return m

    def show_python_info(self):
        '''只打印python的信息，没有解释部分'''
        print(self.get_python_build())
        print(self.get_python_compiler())
        print(self.get_python_branch())
        print(self.get_python_implementation())
        print(self.get_python_version())
        print(self.get_python_revision())
        print(self.get_python_version_tuple())

    def show_os_all_info(self):
        '''打印os的全部信息'''
        print('获取操作系统名称及版本号 : [{}]'.format(self.get_platform()))
        print('获取操作系统版本号 : [{}]'.format(self.get_version()))
        print('获取操作系统的位数 : [{}]'.format(self.get_architecture()))
        print('计算机类型 : [{}]'.format(self.get_machine()))
        print('计算机的网络名称 : [{}]'.format(self.get_node()))
        print('计算机处理器信息 : [{}]'.format(self.get_processor()))
        print('获取操作系统类型 : [{}]'.format(self.get_system()))
        print('汇总信息 : [{}]'.format(self.get_uname()))

    def json_os_info(self):
        m = {}
        m["platform"] = self.get_platform()
        m["version"] = self.get_version()
        m["architecture"] = self.get_architecture()
        m["machine"] = self.get_machine()
        m["node"] = self.get_node()
        m["processor"] = self.get_processor()
        m["system"] = self.get_system()
        return m

    def show_os_info(self):
        '''只打印os的信息，没有解释部分'''
        print(self.get_platform())
        print(self.get_version())
        print(self.get_architecture())
        print(self.get_machine())
        print(self.get_node())
        print(self.get_processor())
        print(self.get_system())
        print(self.get_uname())

    def get_ScanningBasics_json(self):
        m = {}
        m["MachineGuid"] = self.getMachineGuid()
        m["type"] = "Scanning_Basics"
        m["describe"] = "基础信息扫描结果"
        m["os"] = self.json_os_info()
        m["python"] = self.json_python_info()
        ScanningBasics = json.dumps(m)
        # print(ScanningBasics)
        return ScanningBasics

if __name__ == '__main__':
    # main()
    c = ScanningBasics()
    print(c.get_ScanningBasics_json())
