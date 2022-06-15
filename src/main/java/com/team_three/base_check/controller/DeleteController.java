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

    @RequestMapping(value = {"/deleteHardwareRecord/{mac}", "/deleteHardwareRecord/"}, method = RequestMethod.GET)
    public ModelAndView deleteHardware(@PathVariable(value = "mac", required = false) String mac, RedirectAttributes redirectAttribute) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            if(mac == null){
                redirectAttribute.addAttribute("msg","Mac Unbound");
                return new ModelAndView("redirect:/admin/hardwareBaseline");
            }
            int affectRoles = hardwareBaselineService.deleteByMac(mac);
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

    @RequestMapping(value = {"/deleteSystemRecord/{mac}", "/deleteSystemRecord/"}, method = RequestMethod.GET)
    public ModelAndView deleteSystem(@PathVariable(value = "mac", required = false) String mac, RedirectAttributes redirectAttribute) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            if(mac == null){
                redirectAttribute.addAttribute("msg","Mac Unbound");
                return new ModelAndView("redirect:/admin/systemBaseline");
            }
            int affectRoles = systemBaselineService.deleteByMac(mac);
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

    @RequestMapping(value = {"/deleteAccountRecord/{mac}", "/deleteAccountRecord/"}, method = RequestMethod.GET)
    public ModelAndView deleteAccount(@PathVariable(value = "mac", required = false) String mac, RedirectAttributes redirectAttribute) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            if(mac == null){
                redirectAttribute.addAttribute("msg","Mac Unbound");
                return new ModelAndView("redirect:/admin/accountBaseline");
            }
            int affectRoles = accountBaselineService.deleteByMac(mac);
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
