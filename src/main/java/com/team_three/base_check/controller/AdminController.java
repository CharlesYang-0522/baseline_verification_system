package com.team_three.base_check.controller;

import com.team_three.base_check.pojo.HardwareBaseline;
import com.team_three.base_check.pojo.SystemBaseline;
import com.team_three.base_check.pojo.User;
import com.team_three.base_check.pojo.UserProfile;
import com.team_three.base_check.service.impl.*;
import com.team_three.base_check.vo.UserHardwareVO;
import com.team_three.base_check.vo.UserSystemVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

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

    @RequestMapping(value = "/allUser", method = RequestMethod.GET)
    public ModelAndView userProfile(@RequestParam(value = "msg", required = false) String msg, Model model) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            List<UserProfile> list= userProfileService.selectAll();
            if(msg != null){
                model.addAttribute("msg",msg);
            }
            model.addAttribute("allUser",list);
            return new ModelAndView("/admin/userProfile");
        }
        else{
            return new ModelAndView(("/error/AuthorityError"));
        }
    }

    @RequestMapping(value = "/hardwareBaseline", method = RequestMethod.GET)
    public ModelAndView hardwareBaseline(@RequestParam(value = "msg", required = false) String msg, Model model) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            List<UserHardwareVO> list= hardwareBaselineService.selectAllByUser();
            if(msg != null){
                model.addAttribute("msg",msg);
            }
            model.addAttribute("hardwareBaseline",list);
            return new ModelAndView("/admin/hardwareBaseline");
        }
        else{
            return new ModelAndView(("/error/AuthorityError"));
        }
    }

    @RequestMapping(value = {"/userHardwareRecord/{mac}","/userHardwareRecord/"}, method = RequestMethod.GET)
    public ModelAndView userHardwareRecord(@PathVariable(value = "mac", required = false) String mac, Model model, RedirectAttributes redirectAttribute) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            if(mac == null){
                redirectAttribute.addAttribute("msg","Mac Unbound");
                return new ModelAndView("redirect:/admin/hardwareBaseline");
            }
            HardwareBaseline result = this.hardwareBaselineService.selectByMac(mac);
            UserProfile userProfile = this.userProfileService.selectByMac(mac);
            model.addAttribute("username",userProfile.getUsername());
            model.addAttribute("hardwareBaseline",result);
            return new ModelAndView("/admin/userHardware");
        }
        else{
            return new ModelAndView(("/error/AuthorityError"));
        }
    }

    @RequestMapping(value = "/systemBaseline", method = RequestMethod.GET)
    public ModelAndView systemBaseline(@RequestParam(value = "msg", required = false) String msg, Model model) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            List<UserSystemVO> list= systemBaselineService.selectAllByUser();
            if(msg != null){
                model.addAttribute("msg",msg);
            }
            model.addAttribute("systemBaseline",list);
            return new ModelAndView("/admin/systemBaseline");
        }
        else{
            return new ModelAndView(("/error/AuthorityError"));
        }
    }

    @RequestMapping(value = {"/userSystemRecord/{mac}", "/userSystemRecord/"}, method = RequestMethod.GET)
    public ModelAndView userSystemRecord(@PathVariable(value = "mac", required = false) String mac, Model model, RedirectAttributes redirectAttribute) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            if(mac == null){
                redirectAttribute.addAttribute("msg","Mac Unbound");
                return new ModelAndView("redirect:/admin/systemBaseline");
            }
            SystemBaseline result = this.systemBaselineService.selectByMac(mac);
            UserProfile userProfile = this.userProfileService.selectByMac(mac);
            model.addAttribute("username",userProfile.getUsername());
            model.addAttribute("systemBaseline",result);
            return new ModelAndView("/admin/userSystem");
        }
        else{
            return new ModelAndView(("/error/AuthorityError"));
        }
    }
}
