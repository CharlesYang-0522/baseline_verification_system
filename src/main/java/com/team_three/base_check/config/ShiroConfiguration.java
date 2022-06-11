package com.team_three.base_check.config;

import com.team_three.base_check.filter.MyFormAuthenticationFilter;
import com.team_three.base_check.filter.MyLogoutFilter;
import com.team_three.base_check.filter.MyRolesAuthorizationFilter;
import com.team_three.base_check.realm.UserRealm;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {
    //将自己的验证方式加入容器
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

    //cookie对象
    public SimpleCookie rememberMeCookie(){
        System.out.println("ShiroConfiguration rememberMeCookie--------------------------------------------");
        //这个参数是cookie的名称
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(30 * 24 * 60 * 60);
        return simpleCookie;
    }

    //cookie管理对象;记住我功能
    public CookieRememberMeManager rememberMeManager(){
        System.out.println("ShiroConfiguration rememberMeManager--------------------------------------------");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("4KaYtkFJPs0KSA3Kprsdag=="));  // 官方案例中，也有一个盐值
        return cookieRememberMeManager;
    }

    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置自定义验证规则
        securityManager.setRealm(userRealm());
        // 设置自定义rememeberMe
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, Filter> filters = new LinkedHashMap<>();
        // 自定义登录和缓存记住我处理
        filters.put("authc", new MyFormAuthenticationFilter());
        // 自定义权限处理
        filters.put("roles", new MyRolesAuthorizationFilter());
        // 自定义退出处理
        filters.put("logout", new MyLogoutFilter());
        shiroFilterFactoryBean.setFilters(filters);

        Map<String, String> map = new LinkedHashMap<>();
        map.put("/page-login.html","anon"); // anon 代表放行
        map.put("/page-register.html","anon");
        map.put("/index.html","anon");
        map.put("/index","anon");
        map.put("/receive/**","anon");
        map.put("/login","anon");
        map.put("/toLogin","anon");
        map.put("/register","anon");
        map.put("/toRegister","anon");
        map.put("/css/**","anon");
        map.put("/icons/**","anon");
        map.put("/images/**","anon");
        map.put("/js/**","anon");
        map.put("/plugins/**","anon");
        map.put("/swagger-resources/**","anon");
        map.put("/webjars/**","anon");
        map.put("/v2/**","anon");
        map.put("/swagger-ui.html/**","anon");
        map.put("/logout","logout");         // logout是shiro的登出操作

        map.put("/**","authc");             // authc代表shiro必须登录认证成功，才能访问

        map.put("/admin","roles[admin]");   // roles[*] 是shiro中的权限认证控制
        //登录
        shiroFilterFactoryBean.setLoginUrl("/login");
        //首页
        shiroFilterFactoryBean.setSuccessUrl("/login");
        //错误页面，权限认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/login");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        return new DefaultAdvisorAutoProxyCreator();
    }
}
