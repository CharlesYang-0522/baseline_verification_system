package com.team_three.base_check.service;

import com.team_three.base_check.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * (User)表服务接口类
 *
 * @author Charles_Yang
 * @since 2022-06-09 17:09:27
 * @version 1.0
 */
public interface UserService {
    /**
     * 根据模糊条件查询总个数
     *
     * @return 返回查询到的总个数
     */
    public int selectCount(String name);

    /**
     * 查询所有数据
     * @return  返回所有数据
     */
    public Map<String, Object> selectAll();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public Map<String, Object> selectById(Integer id);

    /**
     * 查询分页数据
     *
     * @param page 查询起始位置
     * @param name 查询条数
     * @return 对象列表
     */
    public Map<String, Object> selectByPage(int page, String name);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    public Map<String, Object> insert(User user);

    /**
     * 通过ID查询单条数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    public Map<String, Object> updateById(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public Map<String, Object> deleteById(String id);

    public User getUser(String username);
}