package com.team_three.base_check.realm;

import com.team_three.base_check.pojo.User;
import com.team_three.base_check.service.impl.UserServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

public class UserRealm extends AuthorizingRealm {

    @Resource
    private UserServiceImpl userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Object principal = principals.getPrimaryPrincipal();            //获取登录的用户pojo对象
        User user = (User)principal;
        String auth = user.getRole();
        System.out.println("AuthorizationInfo principal=" + principal + " user.getRole()=" + user.getRole());
        /*
           根据不同的权限判断可访问的资源
           info.addRole("1")中的形参值，在spring_database.xml中shiroFilter进行配置
        */
        info.addRole(auth);

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        User user = null;
        // 把AuthenticationToken实质为UsernamePasswordToken，直接转换即可
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        user = userService.getUser(usernamePasswordToken.getUsername());          // 通过service查询用户名是否存在
        if (user == null)
            throw new UnknownAccountException("用户不存在！");                     // java语法中，throw 一旦执行，则会自动终止整个方法体
        System.out.println("doGetAuthenticationInfo username=" + user.getUsername());
        System.out.println("doGetAuthenticationInfo password=" + user.getPassword());

        //  spring_database.xml文件中已经对此UserRealm bean对象设置了加密方式和次数，固这里无需重复配置，如果xml文件中没有配置，则需要代码配置
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");                      // 加密方式，与注册一致
        hashedCredentialsMatcher.setHashIterations(11);                            // 加密次数，与注册一致
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);             // true是默认值，代表16机制值，如果设置false则为base64
        setCredentialsMatcher(hashedCredentialsMatcher);                           // 保存加密设置
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername());    // 以用户名为加密盐值
        String realmName = getName();                                              // 当前realm对象的name，调用父类的getName()方法即可

        // 验证用户输入的密码经过指定的规则加密之后，与数据库中的密码，进行比对，如果两个32位长16进制码一致，那么代表登录成功
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt, realmName);
        // 在没有加盐的情况下，三个参数就可以进行初步的简单认证信息对象的包装
//        AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getSimpleName());
        return info;
    }
}
