package com.team_three.base_check.mapper;

import com.team_three.base_check.pojo.User;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author Charles_Yang
 * @since 2022-06-09 22:47:08
 * @version 1.0
 */
public interface UserMapper {

    /**
     * 查询所有数据
     * @return  返回所有数据
     */
    List<User> selectAll();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User selectById(@Param("id") Integer id);

    /**
     * 根据模糊条件查询总个数
     *
     * @return 返回查询到的总个数
     */
    int selectCount(String username);

    /**
     * 通过实体作为筛选条件查询
     * 
     * @param index    当前查询开始页中的第一个下标值
     * @param name  需要模糊查询的内容
     * @return 对象列表
     */
    List<User> selectByPage(@Param("index") int index, @Param("name")String name);

    /**
     * 新增数据
     *
     * @param user 实例对象
     */
    void insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int updateById(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("id") String id);

    User selectByUsername(String username);
}