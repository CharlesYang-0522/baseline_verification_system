package com.team_three.base_check.mapper;

import com.team_three.base_check.pojo.ShadowBaseline;
import com.team_three.base_check.vo.UserShadowVO;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (ShadowBaseline)表数据库访问层
 *
 * @author Charles_Yang
 * @since 2022-06-17 10:31:46
 * @version 1.0
 */
public interface ShadowBaselineMapper {

    List<UserShadowVO> selectAllByUser();

    ShadowBaseline selectByMachineGuid(@Param("machineguid") String machineguid);

    void insert(ShadowBaseline shadowBaseline);

    int deleteByMachineGuid(@Param("machineguid") String machineguid);

}