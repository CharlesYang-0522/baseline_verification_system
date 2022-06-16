package com.team_three.base_check.controller;

import com.team_three.base_check.pojo.*;
import com.team_three.base_check.service.impl.*;
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
    private UserProfileServiceImpl userProfileService;

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

}
