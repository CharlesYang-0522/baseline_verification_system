package com.team_three.base_check.mapper;

import com.team_three.base_check.pojo.RegeditBaseline;
import com.team_three.base_check.vo.BaselineVO;
import com.team_three.base_check.vo.UserRegeditVO;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (RegeditBaseline)表数据库访问层
 *
 * @author Charles_Yang
 * @since 2022-06-16 09:18:45
 * @version 1.0
 */
public interface RegeditBaselineMapper {


    List<RegeditBaseline> selectByMachineGuid(@Param("machineguid") String machineguid);

    List<UserRegeditVO> selectAllByUser();

    UserRegeditVO selectByUser();

    void insert(RegeditBaseline regeditBaseline);

    int deleteByMachineGuid(@Param("machineguid") String machineguid);

    int WindowsBaselineNumber();

    int LinuxBaselineNumber();

    BaselineVO WindowsBaseline();

    BaselineVO LinuxBaseline();
}