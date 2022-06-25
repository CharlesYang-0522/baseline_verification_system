package com.team_three.base_check.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/error")
@Api(value = "错误信息接口", tags = "错误信息接口API")
public class ErrorController {

    @ApiOperation(value = "权限错误接口",notes = "提示权限错误信息",httpMethod = "GET")
    @RequestMapping(value = "/AuthorityError", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable Integer id, RedirectAttributes redirectAttribute) {
        return new ModelAndView(("/AuthorityError"));
    }
}
