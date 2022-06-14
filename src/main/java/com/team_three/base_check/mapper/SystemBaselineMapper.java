package com.team_three.base_check.mapper;

import com.team_three.base_check.pojo.SystemBaseline;
import com.team_three.base_check.vo.UserHardwareVO;
import com.team_three.base_check.vo.UserSystemVO;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (SystemBaseline)表数据库访问层
 *
 * @author Charles_Yang
 * @since 2022-06-10 17:06:01
 * @version 1.0
 */
public interface SystemBaselineMapper {

    /**
     * 查询所有数据
     * @return  返回所有数据
     */
    List<SystemBaseline> selectAll();

    List<UserSystemVO> selectAllByUser();

    /**
     * 通过ID查询单条数据
     *
     * @param mac 主键
     * @return 实例对象
     */
    SystemBaseline selectByMac(@Param("mac") String mac);

    /**
     * 根据模糊条件查询总个数
     *
     * @return 返回查询到的总个数
     */
    int selectCount(String mac);

    /**
     * 通过实体作为筛选条件查询
     * 
     * @param index    当前查询开始页中的第一个下标值
     * @param name  需要模糊查询的内容
     * @return 对象列表
     */
    List<SystemBaseline> selectByPage(@Param("index") int index, @Param("name")String name);

    /**
     * 新增数据
     *
     * @param systemBaseline 实例对象
     */
    void insert(SystemBaseline systemBaseline);

    /**
     * 修改数据
     *
     * @param systemBaseline 实例对象
     * @return 影响行数
     */
    int updateByMac(SystemBaseline systemBaseline);

    /**
     * 通过主键删除数据
     *
     * @param mac 主键
     * @return 影响行数
     */
    int deleteByMac(@Param("mac") String mac);

}