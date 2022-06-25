package com.team_three.base_check.controller;

import com.team_three.base_check.pojo.User;
import com.team_three.base_check.pojo.UserProfile;
import com.team_three.base_check.service.impl.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "删除接口", tags = "删除接口API")
public class DeleteController {
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
    @Resource
    private UserServiceImpl userService;

    @ApiOperation(value = "删除用户",notes = "通过id删除用户",httpMethod = "GET")
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

    @ApiOperation(value = "删除硬件检测信息",notes = "通过设备唯一标识符删除硬件检测信息",httpMethod = "GET")
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

    @ApiOperation(value = "删除系统检测信息",notes = "通过设备唯一标识符删除系统检测信息",httpMethod = "GET")
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

    @ApiOperation(value = "删除账户检测信息",notes = "通过设备唯一标识符删除账户检测信息",httpMethod = "GET")
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

    @ApiOperation(value = "删除基线扫描信息",notes = "通过设备唯一标识符删除基线扫描信息",httpMethod = "GET")
    @RequestMapping(value = {"/deleteRegeditRecord/{machineGuid}", "/deleteRegeditRecord/"}, method = RequestMethod.GET)
    public ModelAndView deleteRegedit(@PathVariable(value = "machineGuid", required = false) String machineGuid, RedirectAttributes redirectAttribute) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            if(machineGuid == null){
                redirectAttribute.addAttribute("msg","MachineGuid Unbound");
                return new ModelAndView("redirect:/admin/regeditBaseline");
            }
            int affectRoles = regeditBaselineService.deleteByMachineGuid(machineGuid);
            if(affectRoles != 0){
                redirectAttribute.addAttribute("msg","success");
            }
            else{
                redirectAttribute.addAttribute("msg","No record");
            }
            return new ModelAndView("redirect:/admin/regeditBaseline");
        }
        else{
            return new ModelAndView(("error/AuthorityError"));
        }
    }

    @ApiOperation(value = "删除后门检测信息",notes = "通过设备唯一标识符删除后门检测信息",httpMethod = "GET")
    @RequestMapping(value = {"/deleteShadowRecord/{machineGuid}", "/deleteShadowRecord/"}, method = RequestMethod.GET)
    public ModelAndView deleteShadow(@PathVariable(value = "machineGuid", required = false) String machineGuid, RedirectAttributes redirectAttribute) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            if(machineGuid == null){
                redirectAttribute.addAttribute("msg","MachineGuid Unbound");
                return new ModelAndView("redirect:/admin/shadowBaseline");
            }
            int affectRoles = shadowBaselineService.deleteByMachineGuid(machineGuid);
            if(affectRoles != 0){
                redirectAttribute.addAttribute("msg","success");
            }
            else{
                redirectAttribute.addAttribute("msg","No record");
            }
            return new ModelAndView("redirect:/admin/shadowBaseline");
        }
        else{
            return new ModelAndView(("error/AuthorityError"));
        }
    }
}
