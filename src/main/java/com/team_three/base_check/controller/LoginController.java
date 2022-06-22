package com.team_three.base_check.controller;

import com.team_three.base_check.pojo.User;
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

@RestController
public class LoginController {
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

    @GetMapping(value = "/login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("page-login");
        return mv;
    }

    @GetMapping("/logout")
    public ModelAndView logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        ModelAndView mv = new ModelAndView("page-login");
        return mv;
    }

    @GetMapping("/loginError")
    public String error(Model model){
        model.addAttribute("msg","error");
        return "page-login";
    }
}
