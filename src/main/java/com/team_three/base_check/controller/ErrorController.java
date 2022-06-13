package com.team_three.base_check.controller;

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
public class ErrorController {

    @RequestMapping(value = "/AuthorityError", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable Integer id, RedirectAttributes redirectAttribute) {
        return new ModelAndView(("/AuthorityError"));
    }
}
