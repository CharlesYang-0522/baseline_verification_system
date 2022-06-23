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
@Api(value = "记录检测信息接口", tags = "记录客户端检测信息接口API")
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

    @ApiOperation(value = "记录用户配置信息",notes = "记录用户配置信息",httpMethod = "get")
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

    @ApiOperation(value = "记录硬件检测信息",notes = "记录硬件基线检测信息",httpMethod = "get")
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

    @ApiOperation(value = "通过id记录硬件检测信息",notes = "通过id记录硬件基线检测信息",httpMethod = "get")
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

    @ApiOperation(value = "记录账户检测信息",notes = "记录账户基线检测信息",httpMethod = "get")
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

    @ApiOperation(value = "记录系统检测信息",notes = "记录系统基线检测信息",httpMethod = "get")
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

    @ApiOperation(value = "记录注册表检测信息",notes = "记录注册表基线检测信息",httpMethod = "get")
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

    @ApiOperation(value = "记录影子账户检测信息",notes = "记录影子账户基线检测信息",httpMethod = "get")
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
