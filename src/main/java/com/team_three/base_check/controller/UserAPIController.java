package com.team_three.base_check.controller;
import com.team_three.base_check.vo.UserVO;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
//@RequestMapping("/user")
public class UserAPIController {
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert() {
        return "User Insert!";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete() {
        return "User Delete";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update() {
        return "User Update";
    }

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public String select() {
        return "User Select";
    }

    @RequestMapping(value = "/pathVar/{id}", method = RequestMethod.GET)
    //基本url内容抓取，可以匹配多个变量 -> /pathVar/{id}/user/{name}
    //没有变量会报错
    public String pathVar(@PathVariable String id) {
        return "User id " + id;
    }

    @RequestMapping(value = "/requestParam", method = RequestMethod.GET)
    //http://localhost:8080/user/requestParam?username=xxx&password=xxx
    //没有变量不报错
    //@RequestParam -> 必要变量，一个@RequestParam对应一个变量
    //Bad Request 400 -> 请求参数不完整/失败
    //@RequestParam(required = true, defaultValue = "xxx")
    public String requestParam(@RequestParam String username, String password) {
        return "Username is " + username + " password is " + password;
    }

    @RequestMapping(value = "/requestBody", method = RequestMethod.POST)
    //map: 传任意参数，任意数量
    public String requestBody(@RequestBody Map map) {
        return map.toString();
    }

    @RequestMapping(value = "/requestUser", method = RequestMethod.POST)
    //没传相关参数 -> null, 可以传无关参数，会直接过滤掉
    public String requestUser(@RequestBody UserVO userVO) {
        return "username: " + userVO.getUsername() + " password: " + userVO.getPassword();
    }

    @RequestMapping(value = "/getUserVO", method = RequestMethod.GET)
    public UserVO getUserVO() {
        UserVO userVO = new UserVO();
        userVO.setPassword("123");
        userVO.setUsername("456");
        return userVO;          //自动将对象转json回传前端
    }

    @RequestMapping(value = "/getJSONMap", method = RequestMethod.GET)
    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("test","test");
        map.put("number", 400);
        return map;
    }

    @RequestMapping(value = "/getJSONSet", method = RequestMethod.GET)
    public Set<Object> getSet() {
        //set中装载的值唯一：没有重复项
        //用来过滤重复数据
        //无序
        HashSet<Object> set = new HashSet<>();
        set.add("test");
        set.add(400);
        return set;
    }

    @RequestMapping(value = "/getJSONList", method = RequestMethod.GET)
    public List<Object> getList() {
        //List有重复项,且有序
        List<Object> list = new ArrayList<>();
        list.add("test");
        list.add(400);
        list.add(400);
        return list;
    }
}


