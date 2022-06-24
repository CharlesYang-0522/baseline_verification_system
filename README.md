# baseline_configuration_check

#### 介绍
四川大学网络空间安全学院2019级国信安实训项目：BCVS基线核查系统

#### 软件架构
Windows和Linux客户端使用Python开发
服务端使用Springboot + Thymeleaf + Mybatis实现，Bootstrap前端框架，使用shiro进行权限控制

#### 安装教程

1.  服务器安装Mysql，新建数据库baseline_check，运行baseline_check.sql
2.  修改resources文件夹下的application.properties，填写数据库用户名及密码（或将数据库root用户密码修改成root，直接运行release/server中的.jar文件）
3.  在网页端新建管理员用户，在数据库中将其对应的user.role修改为admin,并删除在userprofile表中的对应行
4.  修改Python客户端main.py中的url，将其中的ip改为自己服务器的地址
5.  使用pyinstaller将客户端打包成可执行文件，在测试机上运行

#### 使用说明

1.  可将客户端可执行文件放入网盘，并修改/src/main/resources/templates/user目录下前端页面中下载链接为网盘分享链接，实现客户端的在线下载功能
2.  windows基线检查系统在python/windows目录下，运行main函数即可进行检测，包含账户检测、硬件检测、系统检测、基线检测、后门检测
3.  Linux基线检查系统在python/linux目录下，运行linux_main函数即可进行检测(需要root权限)，包含账户检测、硬件检测、系统检测、基线检测、后门检测
4.  Java服务端测试可运行位于/src/main/java/com/team_three/base_check目录下的DemoApplication.java文件
5.  release/client_release中的可执行文件内置上传路径已固定，无法直接使用