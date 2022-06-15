package com.team_three.base_check.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.team_three.base_check.mapper.SystemBaselineMapper;
import com.team_three.base_check.pojo.AccountBaseline;
import com.team_three.base_check.pojo.HardwareBaseline;
import com.team_three.base_check.pojo.SystemBaseline;
import com.team_three.base_check.service.impl.AccountBaselineServiceImpl;
import com.team_three.base_check.service.impl.HardwareBaselineServiceImpl;
import com.team_three.base_check.service.impl.SystemBaselineServiceImpl;
import com.team_three.base_check.service.impl.UserProfileServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/receive")
public class ReceiveController {

    @Resource
    private UserProfileServiceImpl userProfileServiceImpl;
    @Resource
    private HardwareBaselineServiceImpl hardwareBaselineService;
    @Resource
    private AccountBaselineServiceImpl accountBaselineService;
    @Resource
    private SystemBaselineServiceImpl systemBaselineService;

    @RequestMapping(value = "/hardwareBaseline", method = RequestMethod.POST)
    public Map<String, Object> HardwareBaseline(@RequestBody JSONObject json) throws Exception {
        if(hardwareBaselineService.selectCount(json.getString("MachineGuid")) != 0){
            System.out.println(hardwareBaselineService.selectCount(json.getString("mac")));
            Map<String, Object> map = new HashMap<>();
            map.put("code", 400);
            map.put("msg", "Mac地址已存在");
            return map;
        }
        System.out.println(json.getString("MachineGuid"));
        System.out.println(hardwareBaselineService.selectCount(json.getString("MachineGuid")));
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
        System.out.println("Inserted a record");
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", json.getString("describe") + "上传成功");
        return map;
    }

    @RequestMapping(value = "/accountBaseline", method = RequestMethod.POST)
    public Map<String, Object> AccountBaseline(@RequestBody JSONObject json) throws Exception {
        System.out.println("MachineGuid:" + json.getString("MachineGuid"));
        System.out.println("type:" + json.getString("type"));
        System.out.println("describe:" + json.getString("describe"));
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
            System.out.println(accountBaselineService.selectCount(list.get(i).get("Caption").toString()));
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
        map.put("msg", json.getString("describe") + "上传成功");
        return map;
    }

    @RequestMapping(value = "/systemBaseline", method = RequestMethod.POST)
    public Map<String, Object> SystemBaseline(@RequestBody JSONObject json) throws Exception {
        if(systemBaselineService.selectCount(json.getString("MachineGuid")) != 0){
            System.out.println(systemBaselineService.selectCount(json.getString("MachineGuid")));
            Map<String, Object> map = new HashMap<>();
            map.put("code", 400);
            map.put("msg", "Mac地址已存在");
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
        map.put("msg", json.getString("describe") + "上传成功");
        return map;
    }
}
