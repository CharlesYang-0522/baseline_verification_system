package com.team_three.base_check.mapper;

import com.team_three.base_check.pojo.UserProfile;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (UserProfile)表数据库访问层
 *
 * @author Charles_Yang
 * @since 2022-06-10 09:55:09
 * @version 1.0
 */
public interface UserProfileMapper {

    /**
     * 查询所有数据
     * @return  返回所有数据
     */
    List<UserProfile> selectAll();
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserProfile selectById(@Param("id") Integer id);

    UserProfile selectByMachineGuid(@Param("machineguid") String machineguid);

    /**
     * 根据模糊条件查询总个数
     *
     * @return 返回查询到的总个数
     */
    int selectCount();

    int existMachine(String machineguid);

    /**
     * 通过实体作为筛选条件查询
     * 
     * @param index    当前查询开始页中的第一个下标值
     * @param name  需要模糊查询的内容
     * @return 对象列表
     */
    List<UserProfile> selectByPage(@Param("index") int index, @Param("name")String name);

    /**
     * 新增数据
     *
     * @param userProfile 实例对象
     */
    void insert(UserProfile userProfile);

    /**
     * 修改数据
     *
     * @param userProfile 实例对象
     * @return 影响行数
     */
    int updateById(UserProfile userProfile);

    int updateMachineGuid(@Param("machineguid") String machineguid, @Param("id") Integer id);

    int updateTime(@Param("update_time") String update_time, @Param("machineguid") String machineguid);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("id") Integer id);

}