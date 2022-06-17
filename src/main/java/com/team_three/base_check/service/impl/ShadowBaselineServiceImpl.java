package com.team_three.base_check.service.impl;

import com.team_three.base_check.pojo.ShadowBaseline;
import com.team_three.base_check.mapper.ShadowBaselineMapper;
import com.team_three.base_check.vo.UserShadowVO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (ShadowBaseline)表服务实现类
 *
 * @author Charles_Yang
 * @since 2022-06-17 10:31:42
 * @version 1.0
 */
@Service("shadowBaselineService")
public class ShadowBaselineServiceImpl {
    @Resource
    private ShadowBaselineMapper shadowBaselineMapper;

    public List<UserShadowVO> selectAllByUser() {
        return this.shadowBaselineMapper.selectAllByUser();
    }

    public ShadowBaseline selectByMachineGuid(String machineGuid) {
        return this.shadowBaselineMapper.selectByMachineGuid(machineGuid);
    }

    public Map<String, Object> insert(ShadowBaseline shadowBaseline) {
        this.shadowBaselineMapper.insert(shadowBaseline);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);   // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("msg", "新增成功");
        return map;
    }

    public int deleteByMachineGuid(String machineGuid) {
        return this.shadowBaselineMapper.deleteByMachineGuid(machineGuid);
    }
}