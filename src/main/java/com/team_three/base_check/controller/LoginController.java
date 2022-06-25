package com.team_three.base_check.controller;

import com.team_three.base_check.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.ui.Model;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Api(value = "登录专用接口", tags = "登录专用接口API")
@RestController
public class LoginController {

    @ApiOperation(value = "登录接口",notes = "用户/管理员进行登录",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名",dataType = "String",required = true),
            @ApiImplicitParam(name = "password",value = "密码",dataType = "String",required = true)
    })
    @PostMapping("/toLogin")
    public ModelAndView toLogin(String username, String password, Model model){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            User user = (User) subject.getPrincipal();
            subject.getSession().setAttribute("user",user);
            if(subject.hasRole( "admin" )){
                ModelAndView mv = new ModelAndView("redirect:/admin/homepage");
                return mv;
            }
            else{
                ModelAndView mv = new ModelAndView("redirect:/Record/userProfile");
                return mv;
            }
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","error");
            System.out.println("UnknownAccountException");
            ModelAndView mv = new ModelAndView("page-login");
            return mv;
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "error");
            System.out.println("IncorrectCredentialsException");
            ModelAndView mv = new ModelAndView("page-login");
            return mv;
        }
    }

    @ApiOperation(value = "登录页面跳转",notes = "跳转至登录页面",httpMethod = "GET")
    @GetMapping(value = "/login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("page-login");
        return mv;
    }

    @ApiOperation(value = "登出接口",notes = "用户/管理员登出系统",httpMethod = "GET")
    @GetMapping("/logout")
    public ModelAndView logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        ModelAndView mv = new ModelAndView("page-login");
        return mv;
    }

    @ApiOperation(value = "登录异常接口",notes = "登录出错",httpMethod = "GET")
    @GetMapping("/loginError")
    public String error(Model model){
        model.addAttribute("msg","error");
        return "page-login";
    }
}
