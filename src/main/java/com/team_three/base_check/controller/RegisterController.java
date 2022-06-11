package com.team_three.base_check.controller;

import com.team_three.base_check.pojo.User;
import com.team_three.base_check.pojo.UserProfile;
import com.team_three.base_check.service.UserService;
import com.team_three.base_check.service.impl.UserProfileServiceImpl;
import com.team_three.base_check.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class RegisterController {

    @Resource
    private UserService userService;
    @Resource
    private UserProfileServiceImpl userProfileService;

    @PostMapping("/toRegister")
    public ModelAndView toRegister(@RequestParam String username, @RequestParam String password,@RequestParam String passwordConfirm, Model model) {

        if(!password.equals(passwordConfirm)){
            model.addAttribute("msg","Password unconfirmed");
            ModelAndView mv = new ModelAndView("page-register");
            return mv;
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        //判断用户名是否已经存在，如果已存在，那就返回错误信息
        if(userService.selectCount(username) != 0){
            model.addAttribute("msg","Duplicate username");
            ModelAndView mv = new ModelAndView("page-register");
            return mv;
            //return Result.fail(message);
        }
        //SecureRandomNumberGenerator类随机方法创建盐，进行两次加密，加密算法用md5
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        int times = 11;
        String algorithmName = "md5";

        //SimpleHash类使用md5加密算法加密两次，把盐加进去，生成新的密码
        String encodedPassword = new SimpleHash(algorithmName, password, credentialsSalt, times).toString();

        //把盐和生成的加密密码，存到数据库里
        user.setPassword(encodedPassword);
        userService.insert(user);

        UserProfile userProfile = new UserProfile();
        User u = userService.getUser(username);
        userProfile.setId(u.getId());
        userProfile.setUsername(username);
        userProfileService.insert(userProfile);
        ModelAndView mv = new ModelAndView("page-login");
        return mv;
    }

    @GetMapping(value = "/register")
    public ModelAndView register(){
        ModelAndView mv = new ModelAndView("page-register");
        return mv;
    }

    @PostMapping(value = "/bindMac")
    public ModelAndView bindMac(@RequestParam String mac, RedirectAttributes redirectAttribute){

        if(userProfileService.existMac(mac) != 0){
            redirectAttribute.addAttribute("msg","Mac already exist");
            ModelAndView mv = new ModelAndView("redirect:/userProfile/selectUser");
            return mv;
        }
        Subject subject = SecurityUtils.getSubject();
        Object object = subject.getSession().getAttribute("user");
        Map map = new BeanMap(object);
        Object id1 = map.get("id");
        Integer id = Integer.parseInt(String.valueOf(id1));
        userProfileService.updateMac(mac, id);
        redirectAttribute.addAttribute("msg","success");
        ModelAndView mv = new ModelAndView("redirect:/userProfile/selectUser");
        return mv;
    }
}
