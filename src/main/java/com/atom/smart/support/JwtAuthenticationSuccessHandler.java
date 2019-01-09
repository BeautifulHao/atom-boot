package com.atom.smart.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * api/登录成功，返回token信息的json
 *
 * @author BeautifulHao
 * @description
 * @create 2018-11-08 16:05
 **/
public class JwtAuthenticationSuccessHandler implements
        AuthenticationSuccessHandler {

    private static final Log logger = LogFactory.getLog(JwtAuthenticationFailureHandler.class);

    private ObjectMapper objectMapper;
    private TokenUtils tokenUtils;

    public JwtAuthenticationSuccessHandler(ObjectMapper objectMapper, TokenUtils tokenUtils) {
        this.objectMapper = objectMapper;
        this.tokenUtils = tokenUtils;
    }

    public JwtAuthenticationSuccessHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        UserDetails userDetails = (UserDetails)authentication.getPrincipal();

        jsonResult jsonResult = new jsonResult(true, "create token success.", tokenUtils.buildToken(userDetails.getUsername()));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(this.objectMapper.writeValueAsString(jsonResult));
        response.getWriter().flush();

    }
}
