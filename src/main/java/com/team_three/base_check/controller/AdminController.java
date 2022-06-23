package com.team_three.base_check.controller;

import com.team_three.base_check.pojo.*;
import com.team_three.base_check.service.impl.*;
import com.team_three.base_check.vo.*;
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
@RequestMapping("/admin")
@Api(value = "管理员接口", tags = "管理员接口API")
public class AdminController {

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

    @ApiOperation(value = "主页展示",notes = "大屏展示基线检测信息",httpMethod = "get")
    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    public ModelAndView homepage(Model model) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            Integer WindowsBaselineNumber = regeditBaselineService.WindowsBaselineNumber();
            Integer LinuxBaselineNumber = regeditBaselineService.LinuxBaselineNumber();
            BaselineVO WindowsBaseline = regeditBaselineService.WindowsBaseline();
            BaselineVO LinuxBaseline = regeditBaselineService.LinuxBaseline();
            int TotalUser = userProfileService.selectCount();
            int CheckedUser = hardwareBaselineService.selectUser();
            model.addAttribute("WindowsBaselineNumber",WindowsBaselineNumber);
            model.addAttribute("LinuxBaselineNumber",LinuxBaselineNumber);
            model.addAttribute("WindowsBaseline",WindowsBaseline);
            model.addAttribute("LinuxBaseline",LinuxBaseline);
            model.addAttribute("TotalUser",TotalUser);
            model.addAttribute("CheckedUser",CheckedUser);
            return new ModelAndView("admin/homepage");
        }
        else{
            return new ModelAndView(("error/AuthorityError"));
        }
    }

    @ApiOperation(value = "展示用户信息",notes = "查看所有用户信息",httpMethod = "get")
    @ApiImplicitParam(name = "msg",value = "参数信息",required = false,dataType = "String")
    @RequestMapping(value = "/allUser", method = RequestMethod.GET)
    public ModelAndView userProfile(@RequestParam(value = "msg", required = false) String msg, Model model) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            List<UserProfile> list= userProfileService.selectAll();
            if(msg != null){
                model.addAttribute("msg",msg);
            }
            model.addAttribute("allUser",list);
            return new ModelAndView("admin/userProfile");
        }
        else{
            return new ModelAndView(("error/AuthorityError"));
        }
    }


    @ApiOperation(value = "展示硬件核查信息",notes = "展示所有硬件基线核查信息",httpMethod = "get")
    @ApiImplicitParam(name = "msg",value = "参数信息",required = false,dataType = "String")
    @RequestMapping(value = "/hardwareBaseline", method = RequestMethod.GET)
    public ModelAndView hardwareBaseline(@RequestParam(value = "msg", required = false) String msg, Model model) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            List<UserHardwareVO> list= hardwareBaselineService.selectAllByUser();
            if(msg != null){
                model.addAttribute("msg",msg);
            }
            model.addAttribute("hardwareBaseline",list);
            return new ModelAndView("admin/hardwareBaseline");
        }
        else{
            return new ModelAndView(("error/AuthorityError"));
        }
    }


    @ApiOperation(value = "展示某用户硬件核查信息",notes = "根据id查询某用户硬件基线核查信息",httpMethod = "get")
    @ApiImplicitParam(name = "machineGuid",value = "用户设备唯一标识符",required = false,dataType = "String")
    @RequestMapping(value = {"/userHardwareRecord/{machineGuid}","/userHardwareRecord/"}, method = RequestMethod.GET)
    public ModelAndView userHardwareRecord(@PathVariable(value = "machineGuid", required = false) String machineGuid, Model model, RedirectAttributes redirectAttribute) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            if(machineGuid == null){
                redirectAttribute.addAttribute("msg","MachineGuid Unbound");
                return new ModelAndView("redirect:/admin/hardwareBaseline");
            }
            HardwareBaseline result = this.hardwareBaselineService.selectByMachineGuid(machineGuid);
            UserProfile userProfile = this.userProfileService.selectByMachineGuid(machineGuid);
            model.addAttribute("username",userProfile.getUsername());
            model.addAttribute("hardwareBaseline",result);
            return new ModelAndView("admin/userHardware");
        }
        else{
            return new ModelAndView(("error/AuthorityError"));
        }
    }

    @ApiOperation(value = "展示系统核查信息",notes = "展示所有系统基线核查信息",httpMethod = "get")
    @ApiImplicitParam(name = "msg",value = "参数信息",required = false,dataType = "String")
    @RequestMapping(value = "/systemBaseline", method = RequestMethod.GET)
    public ModelAndView systemBaseline(@RequestParam(value = "msg", required = false) String msg, Model model) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            List<UserSystemVO> list= systemBaselineService.selectAllByUser();
            if(msg != null){
                model.addAttribute("msg",msg);
            }
            model.addAttribute("systemBaseline",list);
            return new ModelAndView("admin/systemBaseline");
        }
        else{
            return new ModelAndView(("error/AuthorityError"));
        }
    }

    @ApiOperation(value = "展示某用户系统核查信息",notes = "根据id查询某用户系统基线核查信息",httpMethod = "get")
    @ApiImplicitParam(name = "machineGuid",value = "用户设备唯一标识符",required = false,dataType = "String")
    @RequestMapping(value = {"/userSystemRecord/{machineGuid}", "/userSystemRecord/"}, method = RequestMethod.GET)
    public ModelAndView userSystemRecord(@PathVariable(value = "machineGuid", required = false) String machineGuid, Model model, RedirectAttributes redirectAttribute) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            if(machineGuid == null){
                redirectAttribute.addAttribute("msg","MachineGuid Unbound");
                return new ModelAndView("redirect:/admin/systemBaseline");
            }
            SystemBaseline result = this.systemBaselineService.selectByMachineGuid(machineGuid);
            UserProfile userProfile = this.userProfileService.selectByMachineGuid(machineGuid);
            model.addAttribute("username",userProfile.getUsername());
            model.addAttribute("systemBaseline",result);
            return new ModelAndView("admin/userSystem");
        }
        else{
            return new ModelAndView(("error/AuthorityError"));
        }
    }

    @ApiOperation(value = "展示账户核查信息",notes = "展示所有账户基线核查信息",httpMethod = "get")
    @ApiImplicitParam(name = "msg",value = "参数信息",required = false,dataType = "String")
    @RequestMapping(value = "/accountBaseline", method = RequestMethod.GET)
    public ModelAndView accountBaseline(@RequestParam(value = "msg", required = false) String msg, Model model) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            List<UserAccountVO> list= accountBaselineService.selectAllByUser();
            if(msg != null){
                model.addAttribute("msg",msg);
            }
            model.addAttribute("accountBaseline",list);
            return new ModelAndView("admin/accountBaseline");
        }
        else{
            return new ModelAndView(("error/AuthorityError"));
        }
    }

    @ApiOperation(value = "展示某用户账户核查信息",notes = "根据id查询某用户账户基线核查信息",httpMethod = "get")
    @ApiImplicitParam(name = "machineGuid",value = "用户设备唯一标识符",required = false,dataType = "String")
    @RequestMapping(value = {"/userAccountRecord/{machineGuid}", "/userAccountRecord/"}, method = RequestMethod.GET)
    public ModelAndView userAccountRecord(@PathVariable(value = "machineGuid", required = false) String machineGuid, Model model, RedirectAttributes redirectAttribute) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            if(machineGuid == null){
                redirectAttribute.addAttribute("msg","MachineGuid Unbound");
                return new ModelAndView("redirect:/admin/accountBaseline");
            }

            List<AccountBaseline> result = this.accountBaselineService.selectByMachineGuid(machineGuid);
            System.out.println(machineGuid);
            UserProfile userProfile = this.userProfileService.selectByMachineGuid(machineGuid);
            System.out.println(userProfile.toString());
            model.addAttribute("username",userProfile.getUsername());
            model.addAttribute("accountBaseline",result);
            return new ModelAndView("admin/userAccount");
        }
        else{
            return new ModelAndView(("error/AuthorityError"));
        }
    }

    @ApiOperation(value = "管理基线扫描结果",notes = "查看所有基线检测结果",httpMethod = "get")
    @ApiImplicitParam(name = "msg",value = "参数信息",required = false,dataType = "String")
    @RequestMapping(value = "/regeditBaseline", method = RequestMethod.GET)
    public ModelAndView regeditBaseline(@RequestParam(value = "msg", required = false) String msg, Model model) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            List<UserRegeditVO> list= regeditBaselineService.selectAllByUser();
            if(msg != null){
                model.addAttribute("msg",msg);
            }
            model.addAttribute("regeditBaseline",list);
            return new ModelAndView("admin/regeditBaseline");
        }
        else{
            return new ModelAndView(("error/AuthorityError"));
        }
    }

    @ApiOperation(value = "展示某用户基线扫描结果",notes = "根据id查询某用户基线扫描信息",httpMethod = "get")
    @ApiImplicitParam(name = "machineGuid",value = "用户设备唯一标识符",required = false,dataType = "String")
    @RequestMapping(value = {"/userRegeditRecord/{machineGuid}", "/userRegeditRecord/"}, method = RequestMethod.GET)
    public ModelAndView userRegeditRecord(@PathVariable(value = "machineGuid", required = false) String machineGuid, Model model, RedirectAttributes redirectAttribute) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            if(machineGuid == null){
                redirectAttribute.addAttribute("msg","MachineGuid Unbound");
                return new ModelAndView("redirect:/admin/regeditBaseline");
            }

            List<RegeditBaseline> result = this.regeditBaselineService.selectByMachineGuid(machineGuid);
            UserProfile userProfile = this.userProfileService.selectByMachineGuid(machineGuid);
            model.addAttribute("username",userProfile.getUsername());
            model.addAttribute("regeditBaseline",result);
            return new ModelAndView("admin/userRegedit");
        }
        else{
            return new ModelAndView(("error/AuthorityError"));
        }
    }

    @ApiOperation(value = "展示影子账户信息",notes = "展示所有影子账户基线核查信息",httpMethod = "get")
    @ApiImplicitParam(name = "msg",value = "参数信息",required = false,dataType = "String")
    @RequestMapping(value = "/shadowBaseline", method = RequestMethod.GET)
    public ModelAndView shadowBaseline(@RequestParam(value = "msg", required = false) String msg, Model model) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            List<UserShadowVO> list= shadowBaselineService.selectAllByUser();
            if(msg != null){
                model.addAttribute("msg",msg);
            }
            model.addAttribute("shadowBaseline",list);
            return new ModelAndView("admin/shadowBaseline");
        }
        else{
            return new ModelAndView(("error/AuthorityError"));
        }
    }
}
