package com.team_three.base_check.controller;

import com.team_three.base_check.pojo.*;
import com.team_three.base_check.service.impl.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Record")
@Api(value = "用户查询接口", tags = "用户查询数据接口API")
public class RecordController {
    @Resource
    private HardwareBaselineServiceImpl hardwareBaselineService;
    @Resource
    private AccountBaselineServiceImpl accountBaselineService;
    @Resource
    private SystemBaselineServiceImpl systemBaselineService;
    @Resource
    private RegeditBaselineServiceImpl regeditBaselineService;
    @Resource
    private ShadowBaselineServiceImpl shadowBaselineService;
    @Resource
    private UserProfileServiceImpl userProfileService;

    @ApiOperation(value = "查询用户配置信息",notes = "查询用户配置信息",httpMethod = "GET")
    @RequestMapping(value = "/userProfile", method = RequestMethod.GET)
    public ModelAndView userProfile(@RequestParam(value = "msg", required = false) String msg, Model model) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        UserProfile userProfile = this.userProfileService.selectById(user.getId());
        if(msg != null){
            model.addAttribute("msg",msg);
        }
        model.addAttribute("profile",userProfile);
        return new ModelAndView("user/user-profile");
    }

    @ApiOperation(value = "查询用户硬件检测信息",notes = "查询用户硬件基线检测信息",httpMethod = "GET")
    @RequestMapping(value = "/hardwareBaseline", method = RequestMethod.GET)
    public ModelAndView hardwareBaseline(Model model) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        UserProfile userProfile = this.userProfileService.selectById(user.getId());
        String machineGuid = userProfile.getMachineguid();
        HardwareBaseline result = this.hardwareBaselineService.selectByMachineGuid(machineGuid);
        model.addAttribute("hardwareBaseline",result);
        ModelAndView mv = new ModelAndView("user/hardwareBaseline");
        return mv;
    }

    @ApiOperation(value = "根据id查询用户硬件检测信息",notes = "根据id查询用户硬件基线检测信息",httpMethod = "GET")
    @RequestMapping(value = "/hardwareBaselineByID/{id}", method = RequestMethod.GET)
    public ModelAndView hardwareBaseline(@PathVariable String id,Model model) {
        Integer uid = Integer.parseInt(id);
        UserProfile userProfile = this.userProfileService.selectById(uid);
        String machineGuid = userProfile.getMachineguid();
        HardwareBaseline result = this.hardwareBaselineService.selectByMachineGuid(machineGuid);
        model.addAttribute("hardwareBaseline",result);
        ModelAndView mv = new ModelAndView("user/hardwareBaseline");
        return mv;
    }

    @ApiOperation(value = "查询用户账户检测信息",notes = "查询用户账户基线检测信息",httpMethod = "GET")
    @RequestMapping(value = "/accountBaseline", method = RequestMethod.GET)
    public ModelAndView accountBaseline(Model model) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        UserProfile userProfile = this.userProfileService.selectById(user.getId());
        String machineGuid = userProfile.getMachineguid();
        List<AccountBaseline> result = this.accountBaselineService.selectByMachineGuid(machineGuid);
        model.addAttribute("accounts",result);
        ModelAndView mv = new ModelAndView("user/accountBaseline");
        return mv;
    }

    @ApiOperation(value = "查询用户系统检测信息",notes = "查询用户系统基线检测信息",httpMethod = "GET")
    @RequestMapping(value = "/systemBaseline", method = RequestMethod.GET)
    public ModelAndView systemBaseline(Model model) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        UserProfile userProfile = this.userProfileService.selectById(user.getId());
        String machineGuid = userProfile.getMachineguid();
        SystemBaseline result = this.systemBaselineService.selectByMachineGuid(machineGuid);
        model.addAttribute("systemBaseline",result);
        ModelAndView mv = new ModelAndView("user/systemBaseline");
        return mv;
    }

    @ApiOperation(value = "查询用户注册表检测信息",notes = "查询用户注册表基线检测信息",httpMethod = "GET")
    @RequestMapping(value = "/regeditBaseline", method = RequestMethod.GET)
    public ModelAndView regeditBaseline(Model model) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        UserProfile userProfile = this.userProfileService.selectById(user.getId());
        String machineGuid = userProfile.getMachineguid();
        List<RegeditBaseline> result = this.regeditBaselineService.selectByMachineGuid(machineGuid);
        model.addAttribute("regedits",result);
        ModelAndView mv = new ModelAndView("user/regeditBaseline");
        return mv;
    }

    @ApiOperation(value = "查询用户后门检测信息",notes = "查询用户后门检测信息",httpMethod = "GET")
    @RequestMapping(value = "/shadowBaseline", method = RequestMethod.GET)
    public ModelAndView shadowBaseline(Model model) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        UserProfile userProfile = this.userProfileService.selectById(user.getId());
        String machineGuid = userProfile.getMachineguid();
        ShadowBaseline result = this.shadowBaselineService.selectByMachineGuid(machineGuid);
        model.addAttribute("shadow",result);
        ModelAndView mv = new ModelAndView("user/shadowBaseline");
        return mv;
    }

}
