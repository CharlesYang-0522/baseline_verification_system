package com.team_three.base_check.mapper;

import com.team_three.base_check.pojo.AccountBaseline;
import com.team_three.base_check.vo.UserAccountVO;
import com.team_three.base_check.vo.UserHardwareVO;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (AccountBaseline)表数据库访问层
 *
 * @author Charles_Yang
 * @since 2022-06-10 16:10:35
 * @version 1.0
 */
public interface AccountBaselineMapper {

    /**
     * 查询所有数据
     * @return  返回所有数据
     */
    List<AccountBaseline> selectAll();

    List<UserAccountVO> selectAllByUser();

    List<AccountBaseline> selectByMachineGuid(@Param("machineguid") String machineguid);

    /**
     * 根据模糊条件查询总个数
     *
     * @return 返回查询到的总个数
     */
    int selectCount(String caption);

    /**
     * 通过实体作为筛选条件查询
     * 
     * @param index    当前查询开始页中的第一个下标值
     * @param name  需要模糊查询的内容
     * @return 对象列表
     */
    List<AccountBaseline> selectByPage(@Param("index") int index, @Param("name")String name);

    /**
     * 新增数据
     *
     * @param accountBaseline 实例对象
     */
    void insert(AccountBaseline accountBaseline);

    /**
     * 修改数据
     *
     * @param accountBaseline 实例对象
     * @return 影响行数
     */
    int updateById(AccountBaseline accountBaseline);

    /**
     * 通过主键删除数据
     *
     * @param caption 主键
     * @return 影响行数
     */
    int deleteById(@Param("id") String caption);

    int deleteByMachineGuid(@Param("machineguid") String machineguid);

}