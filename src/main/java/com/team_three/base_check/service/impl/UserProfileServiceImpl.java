package com.team_three.base_check.service.impl;

import com.team_three.base_check.pojo.User;
import com.team_three.base_check.pojo.UserProfile;
import com.team_three.base_check.mapper.UserProfileMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (UserProfile)表服务实现类
 *
 * @author Charles_Yang
 * @since 2022-06-10 09:55:09
 * @version 1.0
 */
@Service("userProfileService")
public class UserProfileServiceImpl {
    @Resource
    private UserProfileMapper userProfileMapper;

    /**
     * 根据模糊条件查询总个数
     *
     * @return 返回查询到的总个数
     */
    public Map<String, Object> selectCount(String name) {
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("list", this.userProfileMapper.selectCount(name));
        return map;
    }

    public int existMac(String mac) {
        return this.userProfileMapper.existMac(mac);
    }

    /**
     * 查询所有数据
     * @return  返回所有数据
     */
    public List<UserProfile> selectAll() {
        return this.userProfileMapper.selectAll();
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public UserProfile selectById(Integer id) {
        UserProfile userProfile = this.userProfileMapper.selectById(id);
        return userProfile;
    }

    /**
     * 查询分页数据
     *
     * @param page 查询起始位置
     * @param name 查询条数
     * @return 对象列表
     */
    public Map<String, Object> selectByPage(int page, String name) {
    // 获取当前表中的总记录
        int tableCount = this.userProfileMapper.selectCount(name);
        // 总页码计算   (总条数 - 1) / 每页显示条数  + 1
        // (100 - 1) / 10 + 1 = 10        (101 - 1) / 10 + 1 = 11      (99 - 1) / 10 + 1 = 10
        int pageCount = (tableCount - 1) / 10 + 1;
        // 计算每页开始的下标值
        int index = (page - 1) * 10;
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);   // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("msg", "查询成功");
        map.put("pageCount", pageCount);  // 查询的记录总页码
        map.put("count", tableCount);     // 当前表中的总条数
        map.put("data", this.userProfileMapper.selectByPage(index, name));
        return map;
    }

    /**
     * 新增数据
     *
     * @param userProfile 实例对象
     * @return 实例对象
     */
    public Map<String, Object> insert(UserProfile userProfile) {
        // UUID.randomUUID()  返回内容：asd21321-ewrewrew213213-123213zsad-123asdasd这样的形态
        this.userProfileMapper.insert(userProfile);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);   // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("msg", "新增成功");
        return map;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param userProfile 实例对象
     * @return 实例对象
     */
    public Map<String, Object> updateById(UserProfile userProfile) {
        this.userProfileMapper.updateById(userProfile);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);   // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("msg", "更新成功");
        return map;
    }

    public int updateMac(String mac, Integer id) {
        return this.userProfileMapper.updateMac(mac, id);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public int deleteById(Integer id) {
        return this.userProfileMapper.deleteById(id);
    }
}