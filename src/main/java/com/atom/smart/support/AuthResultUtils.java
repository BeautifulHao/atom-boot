package com.atom.smart.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author BeautifulHao
 * @description
 * @create 2018-11-07 16:48
 **/
public class AuthResultUtils {

    @Autowired
    private ObjectMapper objectMapper;

    public boolean HandleAuthFromUserDetails(ServletResponse res, UserDetails userDetails)throws IOException, ServletException
    {
        boolean handled = false;

        HttpServletResponse response = (HttpServletResponse)res;
        jsonResult jsonResult = new jsonResult(false, "", "");

        if (!userDetails.isEnabled()){
            jsonResult.setMessage("当前账号不可用!");
            response.getWriter().println(this.objectMapper.writeValueAsString(jsonResult));
            handled=true;
        }
        else if (!userDetails.isAccountNonLocked()){
            jsonResult.setMessage("当前账号被锁定!");
            response.getWriter().println(this.objectMapper.writeValueAsString(jsonResult));
            handled=true;
        }
        else if (!userDetails.isAccountNonExpired()){
            jsonResult.setMessage("当前账号有效期过期!");
            response.getWriter().println(this.objectMapper.writeValueAsString(jsonResult));
            handled=true;
        }
        else if (!userDetails.isAccountNonExpired()){
            jsonResult.setMessage("当前账号密码有效期过期!");
            response.getWriter().println(this.objectMapper.writeValueAsString(jsonResult));
            handled=true;
        }

        if(handled){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().flush();
        }

        return handled;
    }

    public void HandleAuthException(ServletResponse res,Exception e) throws IOException {
        HttpServletResponse response = (HttpServletResponse)res;
        jsonResult jsonResult = new jsonResult(false, "Auth-Header Invalid.", e.getMessage());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(this.objectMapper.writeValueAsString(jsonResult));
        response.getWriter().flush();
    }

}
