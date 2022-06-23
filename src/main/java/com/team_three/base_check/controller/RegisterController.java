package com.team_three.base_check.controller;

import com.team_three.base_check.pojo.User;
import com.team_three.base_check.pojo.UserProfile;
import com.team_three.base_check.service.UserService;
import com.team_three.base_check.service.impl.UserProfileServiceImpl;
import com.team_three.base_check.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

@Api(value = "注册专用接口", tags = "注册专用接口API")
@RestController
public class RegisterController {

    @Resource
    private UserServiceImpl userService;
    @Resource
    private UserProfileServiceImpl userProfileService;

    @ApiOperation(value = "用户注册接口",notes = "注册用户信息",httpMethod = "post")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名",dataType = "String",required = true),
            @ApiImplicitParam(name = "password",value = "密码",dataType = "String",required = true),
            @ApiImplicitParam(name = "passwordConfirm",value = "确认密码",dataType = "String",required = true)
    })
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

    @ApiOperation(value = "用户注册成功接口",notes = "展示注册成功后页面",httpMethod = "get")
    @GetMapping(value = "/register")
    public ModelAndView register(){
        ModelAndView mv = new ModelAndView("page-register");
        return mv;
    }

    @ApiOperation(value = "绑定用户系统唯一标识符",notes = "绑定用户系统唯一标识符",httpMethod = "post")
    @ApiImplicitParam(name = "machineGuid",value = "系统唯一标识符",required = true,dataType = "String")
    @PostMapping(value = "/bindMachineGuid")
    public ModelAndView bindMac(@RequestParam String machineGuid, RedirectAttributes redirectAttribute){

        if(userProfileService.existMachine(machineGuid) != 0){
            redirectAttribute.addAttribute("msg","MachineGuid already exist");
            ModelAndView mv = new ModelAndView("redirect:/Record/userProfile");
            return mv;
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        userProfileService.updateMachineGuid(machineGuid, user.getId());
        redirectAttribute.addAttribute("msg","success");
        return new ModelAndView("redirect:/Record/userProfile");
    }
}
