package com.atom.smart.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author BeautifulHao
 * @description
 * @create 2018-11-08 11:06
 **/
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private static final Log logger = LogFactory.getLog(JwtAccessDeniedHandler.class);

    private ObjectMapper objectMapper;

    public JwtAccessDeniedHandler( ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        logger.debug("matche jwt request handle ,return 403 for AccessDenied Exception");

        jsonResult jsonResult = new jsonResult(false, "无权限访问！", "");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(this.objectMapper.writeValueAsString(jsonResult));
        response.getWriter().flush();
    }
}
