package com.team_three.base_check.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class MyRolesAuthorizationFilter extends RolesAuthorizationFilter {
    @Override
    public boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        System.out.println("----MyRolesAuthorizationFilter-------onAccessDenied-------------");
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(200);
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        ObjectMapper json = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 10500);
        map.put("msg", "未授权!");
        System.out.println(map);
        out.print(json.writeValueAsString(map));
        out.flush();
        out.close();
        return false;
    }
}
