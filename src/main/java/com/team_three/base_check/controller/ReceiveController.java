package com.team_three.base_check.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.team_three.base_check.mapper.SystemBaselineMapper;
import com.team_three.base_check.pojo.*;
import com.team_three.base_check.service.impl.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/receive")
@Api(value = "接收检测信息接口", tags = "接收客户端检测信息接口API")
public class ReceiveController {

    @Resource
    private UserProfileServiceImpl userProfileService;
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

    @ApiOperation(value = "接收硬件检测信息",notes = "接收硬件基线检测信息",httpMethod = "post")
    @RequestMapping(value = "/hardwareBaseline", method = RequestMethod.POST)
    public Map<String, Object> HardwareBaseline(@RequestBody JSONObject json) throws Exception {
        if(hardwareBaselineService.selectCount(json.getString("MachineGuid")) != 0){
            Map<String, Object> map = new HashMap<>();
            map.put("code", 400);
            map.put("msg", "MachineGuid已存在");
            return map;
        }
        HardwareBaseline hardwareBaseline = new HardwareBaseline();
        hardwareBaseline.setMachineguid(json.getString("MachineGuid"));
        hardwareBaseline.setInfoOs(json.getString("info_os"));
        hardwareBaseline.setInfoVersion(json.getString("info_version"));
        hardwareBaseline.setInfoFullname(json.getString("info_fullname"));
        hardwareBaseline.setInfoOsArchitecture(json.getString("info_os_architecture"));
        hardwareBaseline.setInfoMuLanguages(json.getString("info_mu_languages"));
        hardwareBaseline.setInfoSerialnumber(json.getString("info_SerialNumber"));
        hardwareBaseline.setInfoCpuCount(json.getString("info_cpu_count"));
        hardwareBaseline.setInfoMainboard(json.getString("info_mainboard"));
        hardwareBaseline.setInfoBoardModel(json.getString("info_board_model"));
        hardwareBaseline.setInfoSystemtype(json.getString("info_systemtype"));
        hardwareBaseline.setInfoPhysicalMemory(json.getString("info_physical_memory"));
        hardwareBaseline.setInfoCpuName(json.getString("info_cpu_name"));
        hardwareBaseline.setInfoClockSpeed(json.getString("info_clock_speed"));
        hardwareBaseline.setInfoNumberCore(json.getString("info_number_core"));
        hardwareBaseline.setInfoDataWidth(json.getString("info_data_width"));
        hardwareBaseline.setInfoSocketDesigination(json.getString("info_socket_desigination"));
        hardwareBaseline.setInfoL2Cache(json.getString("info_l2_cache"));
        hardwareBaseline.setInfoL3Cache(json.getString("info_l3_cache"));

        hardwareBaselineService.insert(hardwareBaseline);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "上传成功");
        return map;
    }

    @ApiOperation(value = "接收账户检测信息",notes = "接收账户基线检测信息",httpMethod = "post")
    @RequestMapping(value = "/accountBaseline", method = RequestMethod.POST)
    public Map<String, Object> AccountBaseline(@RequestBody JSONObject json) throws Exception {
        JSONObject object= JSONObject.parseObject(json.toString());
        List<HashMap> list = JSON.parseArray((object.get("user")).toString(), HashMap.class);
        for(int i=0;i<list.size();i++){
            if(accountBaselineService.selectCount(list.get(i).get("Caption").toString()) != 0){
                System.out.println(accountBaselineService.selectCount(list.get(i).get("Caption").toString()));
                Map<String, Object> map = new HashMap<>();
                map.put("code", 400);
                map.put("msg", "账户已存在");
                return map;
            }
            AccountBaseline accountBaseline = new AccountBaseline();
            accountBaseline.setMachineguid(json.getString("MachineGuid"));
            accountBaseline.setCaption(list.get(i).get("Caption").toString());
            accountBaseline.setDescription(list.get(i).get("Description").toString());
            accountBaseline.setDomain(list.get(i).get("Domain").toString());
            accountBaseline.setName(list.get(i).get("Name").toString());
            accountBaseline.setPasswordrequired(list.get(i).get("PasswordRequired").toString());
            accountBaseline.setSid(list.get(i).get("SID").toString());
            accountBaseline.setStatus(list.get(i).get("Status").toString());
            accountBaselineService.insert(accountBaseline);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "上传成功");
        return map;
    }

    @ApiOperation(value = "接收系统检测信息",notes = "接收系统基线检测信息",httpMethod = "post")
    @RequestMapping(value = "/systemBaseline", method = RequestMethod.POST)
    public Map<String, Object> SystemBaseline(@RequestBody JSONObject json) throws Exception {
        if(systemBaselineService.selectCount(json.getString("MachineGuid")) != 0){
            Map<String, Object> map = new HashMap<>();
            map.put("code", 400);
            map.put("msg", "MachineGuid已存在");
            return map;
        }
        SystemBaseline systemBaseline = new SystemBaseline();
        systemBaseline.setMachineguid(json.getString("MachineGuid"));
        systemBaseline.setOscaption(json.getString("OSCaption"));
        systemBaseline.setOsversion(json.getString("OSVersion"));
        systemBaseline.setDiskcaption(json.getString("DiskCaption"));
        systemBaseline.setInterfacetype(json.getString("InterfaceType"));
        systemBaseline.setNetworkcaption(json.getString("networkCaption"));
        systemBaseline.setIpaddress(json.getString("IPAddress"));
        systemBaseline.setMacaddress(json.getString("MACAddress"));
        systemBaseline.setUpdatehotfixid(json.getString("UpdateHotFixID"));
        systemBaseline.setInstalledon(json.getString("InstalledOn"));
        systemBaselineService.insert(systemBaseline);

        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "上传成功");
        return map;
    }

    @ApiOperation(value = "接收注册表检测信息",notes = "接收注册表基线检测信息",httpMethod = "post")
    @RequestMapping(value = "/regeditBaseline", method = RequestMethod.POST)
    public Map<String, Object> RegeditBaseline(@RequestBody JSONObject json) throws Exception {
        JSONObject object= JSONObject.parseObject(json.toString());
        List<RegeditBaseline> list = JSON.parseArray((object.get("account")).toString(), RegeditBaseline.class);
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTime.format(formatter));
        for(int i=0;i<list.size();i++){
            list.get(i).setUpdatetime(dateTime.format(formatter));
            System.out.println(list.get(i).toString());
            regeditBaselineService.insert(list.get(i));
        }
        if(userProfileService.existMachine(list.get(0).getMachineguid()) != 0){
            userProfileService.updateTime(dateTime.format(formatter), list.get(0).getMachineguid());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "上传成功");
        return map;
    }

    @ApiOperation(value = "接收后门检测信息",notes = "接收后门检测信息",httpMethod = "post")
    @RequestMapping(value = "/shadowBaseline", method = RequestMethod.POST)
    public Map<String, Object> ShadowBaseline(@RequestBody JSONObject json) throws Exception {
        ShadowBaseline shadowBaseline = new ShadowBaseline();
        shadowBaseline.setMachineguid(json.getString("MachineGuid"));
        shadowBaseline.setDetect(json.getString("detect"));
        shadowBaseline.setShadowuser(json.getString("shadowuser"));
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        shadowBaseline.setUpdatetime(dateTime.format(formatter));
        shadowBaselineService.insert(shadowBaseline);

        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "上传成功");
        return map;
    }

}
