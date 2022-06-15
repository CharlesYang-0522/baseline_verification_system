package com.team_three.base_check.controller;

import com.team_three.base_check.pojo.User;
import com.team_three.base_check.pojo.UserProfile;
import com.team_three.base_check.service.impl.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/delete")
public class DeleteController {
    @Resource
    private HardwareBaselineServiceImpl hardwareBaselineService;
    @Resource
    private AccountBaselineServiceImpl accountBaselineService;
    @Resource
    private SystemBaselineServiceImpl systemBaselineService;
    @Resource
    private UserProfileServiceImpl userProfileService;
    @Resource
    private UserServiceImpl userService;

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable Integer id, RedirectAttributes redirectAttribute) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            int affectUser = userService.deleteById(id);
            int affectUserProfile = userProfileService.deleteById(id);
            if(affectUser != 0 && affectUserProfile != 0){
                redirectAttribute.addAttribute("msg","success");
            }
            else{
                redirectAttribute.addAttribute("msg","error");
            }
            return new ModelAndView("redirect:/admin/allUser");
        }
        else{
            return new ModelAndView(("error/AuthorityError"));
        }
    }

    @RequestMapping(value = {"/deleteHardwareRecord/{machineGuid}", "/deleteHardwareRecord/"}, method = RequestMethod.GET)
    public ModelAndView deleteHardware(@PathVariable(value = "machineGuid", required = false) String machineGuid, RedirectAttributes redirectAttribute) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            if(machineGuid == null){
                redirectAttribute.addAttribute("msg","MachineGuid Unbound");
                return new ModelAndView("redirect:/admin/hardwareBaseline");
            }
            int affectRoles = hardwareBaselineService.deleteByMachineGuid(machineGuid);
            if(affectRoles != 0){
                redirectAttribute.addAttribute("msg","success");
            }
            else{
                redirectAttribute.addAttribute("msg","No record");
            }
            return new ModelAndView("redirect:/admin/hardwareBaseline");
        }
        else{
            return new ModelAndView(("error/AuthorityError"));
        }
    }

    @RequestMapping(value = {"/deleteSystemRecord/{machineGuid}", "/deleteSystemRecord/"}, method = RequestMethod.GET)
    public ModelAndView deleteSystem(@PathVariable(value = "machineGuid", required = false) String machineGuid, RedirectAttributes redirectAttribute) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            if(machineGuid == null){
                redirectAttribute.addAttribute("msg","Mac Unbound");
                return new ModelAndView("redirect:/admin/systemBaseline");
            }
            int affectRoles = systemBaselineService.deleteByMachineGuid(machineGuid);
            if(affectRoles != 0){
                redirectAttribute.addAttribute("msg","success");
            }
            else{
                redirectAttribute.addAttribute("msg","No record");
            }
            return new ModelAndView("redirect:/admin/systemBaseline");
        }
        else{
            return new ModelAndView(("error/AuthorityError"));
        }
    }

    @RequestMapping(value = {"/deleteAccountRecord/{machineGuid}", "/deleteAccountRecord/"}, method = RequestMethod.GET)
    public ModelAndView deleteAccount(@PathVariable(value = "machineGuid", required = false) String machineGuid, RedirectAttributes redirectAttribute) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            if(machineGuid == null){
                redirectAttribute.addAttribute("msg","MachineGuid Unbound");
                return new ModelAndView("redirect:/admin/accountBaseline");
            }
            int affectRoles = accountBaselineService.deleteByMachineGuid(machineGuid);
            if(affectRoles != 0){
                redirectAttribute.addAttribute("msg","success");
            }
            else{
                redirectAttribute.addAttribute("msg","No record");
            }
            return new ModelAndView("redirect:/admin/accountBaseline");
        }
        else{
            return new ModelAndView(("error/AuthorityError"));
        }
    }
}
