package com.team_three.base_check.controller;

import com.team_three.base_check.pojo.AccountBaseline;
import com.team_three.base_check.pojo.HardwareBaseline;
import com.team_three.base_check.pojo.SystemBaseline;
import com.team_three.base_check.pojo.UserProfile;
import com.team_three.base_check.service.impl.AccountBaselineServiceImpl;
import com.team_three.base_check.service.impl.HardwareBaselineServiceImpl;
import com.team_three.base_check.service.impl.SystemBaselineServiceImpl;
import com.team_three.base_check.service.impl.UserProfileServiceImpl;
import org.apache.commons.beanutils.BeanMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
    private UserProfileServiceImpl userProfileService;


    @RequestMapping(value = "/hardwareBaseline", method = RequestMethod.GET)
    public ModelAndView hardwareBaseline(Model model) {
        Subject subject = SecurityUtils.getSubject();
        Object object = subject.getSession().getAttribute("user");
        Map map = new BeanMap(object);
        Object id1 = map.get("id");
        Integer id = Integer.parseInt(String.valueOf(id1));
        UserProfile userProfile = this.userProfileService.selectById(id);
        String mac = userProfile.getMac();
        HardwareBaseline result = this.hardwareBaselineService.selectByMac(mac);
        model.addAttribute("hardwareBaseline",result);
        ModelAndView mv = new ModelAndView("/user/hardwareBaseline");
        return mv;
    }

    @RequestMapping(value = "/hardwareBaselineByID/{id}", method = RequestMethod.GET)
    public ModelAndView hardwareBaseline(@PathVariable String id,Model model) {
        Integer uid = Integer.parseInt(id);
        UserProfile userProfile = this.userProfileService.selectById(uid);
        String mac = userProfile.getMac();
        HardwareBaseline result = this.hardwareBaselineService.selectByMac(mac);
        model.addAttribute("hardwareBaseline",result);
        ModelAndView mv = new ModelAndView("/user/hardwareBaseline");
        return mv;
    }

    @RequestMapping(value = "/accountBaseline", method = RequestMethod.GET)
    public ModelAndView accountBaseline(Model model) {
        Subject subject = SecurityUtils.getSubject();
        Object object = subject.getSession().getAttribute("user");
        Map map = new BeanMap(object);
        Object id1 = map.get("id");
        Integer id = Integer.parseInt(String.valueOf(id1));
        UserProfile userProfile = this.userProfileService.selectById(id);
        String mac = userProfile.getMac();
        List<AccountBaseline> result = this.accountBaselineService.selectByMac(mac);
        model.addAttribute("accounts",result);
        ModelAndView mv = new ModelAndView("/user/accountBaseline");
        return mv;
    }

    @RequestMapping(value = "/systemBaseline", method = RequestMethod.GET)
    public ModelAndView systemBaseline(Model model) {
        Subject subject = SecurityUtils.getSubject();
        Object object = subject.getSession().getAttribute("user");
        Map map = new BeanMap(object);
        Object id1 = map.get("id");
        Integer id = Integer.parseInt(String.valueOf(id1));
        UserProfile userProfile = this.userProfileService.selectById(id);
        String mac = userProfile.getMac();
        SystemBaseline result = this.systemBaselineService.selectByMac(mac);
        model.addAttribute("systemBaseline",result);
        ModelAndView mv = new ModelAndView("/user/systemBaseline");
        return mv;
    }

}
