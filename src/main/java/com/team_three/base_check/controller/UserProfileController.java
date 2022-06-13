package com.team_three.base_check.controller;

import com.team_three.base_check.pojo.User;
import com.team_three.base_check.pojo.UserProfile;
import com.team_three.base_check.service.impl.UserProfileServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.apache.commons.beanutils.BeanMap;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (UserProfile)表控制层
 *
 * @author Charles_Yang
 * @since 2022-06-10 09:55:09
 * @version 1.0
 */
@RestController
@RequestMapping("/userProfile")
@Api(value = "(UserProfile)管理",tags = "(UserProfile)管理接口API")
@ApiResponses({
        @ApiResponse(code=400,message="请求参数不完整或者错误"),
        @ApiResponse(code=404,message="找不到页面"),
        @ApiResponse(code=405,message="请求方式不正确，比如后台接收是post，但前端请求的是get"),
        @ApiResponse(code=406,message="页面地址和接口名冲突"),
        @ApiResponse(code=415,message="请求格式不正确，前端可能没有把请求头修改为json，也可能前端请求的json对象没有转换为字符串"),
        @ApiResponse(code=500,message="后台服务逻辑错误")
})
public class UserProfileController {
    /**
     * 服务对象
     */
    @Resource
    private UserProfileServiceImpl userProfileService;

    @RequestMapping(value = "/selectUser", method = RequestMethod.GET)
    public ModelAndView selectUser(@RequestParam(value = "msg", required = false) String msg, Model model) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        UserProfile userProfile = this.userProfileService.selectById(user.getId());
        if(msg != null){
            model.addAttribute("msg",msg);
        }
        model.addAttribute("profile",userProfile);
        return new ModelAndView("/user/user-profile");
    }

}