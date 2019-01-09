package com.atom.smart.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author BeautifulHao
 * @description
 * @create 2018-11-08 09:02
 **/
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Log logger = LogFactory
            .getLog(JwtAuthenticationEntryPoint.class);

    private ObjectMapper objectMapper;

    public JwtAuthenticationEntryPoint( ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     *
     *只对/api/下的请求进行处理 AntPathRequestMatcher("/api/**")
     *
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        logger.debug("matche jwt request handle ,return 401 for unAuthenticationException");

        jsonResult jsonResult = new jsonResult(false, "无身份信息，或者验证失败！", "");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(this.objectMapper.writeValueAsString(jsonResult));
        response.getWriter().flush();
    }
}
