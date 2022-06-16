package com.team_three.base_check.service.impl;

import com.team_three.base_check.pojo.RegeditBaseline;
import com.team_three.base_check.mapper.RegeditBaselineMapper;
import com.team_three.base_check.vo.UserRegeditVO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (RegeditBaseline)表服务实现类
 *
 * @author Charles_Yang
 * @since 2022-06-16 09:18:45
 * @version 1.0
 */
@Service("regeditBaselineService")
public class RegeditBaselineServiceImpl {
    @Resource
    private RegeditBaselineMapper regeditBaselineMapper;

    public List<RegeditBaseline> selectByMachineGuid(String machineGuid) {
        return this.regeditBaselineMapper.selectByMachineGuid(machineGuid);
    }

    public List<UserRegeditVO> selectAllByUser() {
        return this.regeditBaselineMapper.selectAllByUser();
    }

    public Map<String, Object> insert(RegeditBaseline regeditBaseline) {
        // UUID.randomUUID()  返回内容：asd21321-ewrewrew213213-123213zsad-123asdasd这样的形态
        this.regeditBaselineMapper.insert(regeditBaseline);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);   // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("msg", "新增成功");
        return map;
    }

    public int deleteByMachineGuid(String machineGuid) {
        return this.regeditBaselineMapper.deleteByMachineGuid(machineGuid);
    }

}