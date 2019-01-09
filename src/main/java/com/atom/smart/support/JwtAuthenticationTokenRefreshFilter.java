package com.atom.smart.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author BeautifulHao
 * @description
 * @create 2018-11-12 15:28
 **/
public class JwtAuthenticationTokenRefreshFilter extends GenericFilterBean {

    private RequestMatcher requestMatcher;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public JwtAuthenticationTokenRefreshFilter() {
        this.requestMatcher = new AntPathRequestMatcher("/api/refresh", "POST");
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (requiresLogout(request, response)) {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authHeader = request.getHeader(this.tokenUtils.getJwtProperties().getTokenHeader());
            final String authToken = authHeader.substring(7);

            if (logger.isDebugEnabled()) {
                logger.debug("Refresh token");
            }

            String newToken =refreshToken(request,response,auth);

            //更新注销的token池，有效期为token有效期，redis自动删除失效token
            Date expire = tokenUtils.getExpire(authToken);
            long expireMis= expire !=null? expire.getTime() - System.currentTimeMillis() : 0l;
            if(expireMis < 0l){
                expireMis = 0l;
            }
            redisTemplate.opsForValue().set(authToken, System.currentTimeMillis(),expireMis, TimeUnit.MILLISECONDS);

            JwtUsernamePasswordAuthenticationToken jwtUsernamePasswordAuthenticationToken = (JwtUsernamePasswordAuthenticationToken)auth;
            jwtUsernamePasswordAuthenticationToken.setToken(newToken);
            SecurityContextHolder.getContext().setAuthentication(jwtUsernamePasswordAuthenticationToken);

            return;
        }

        chain.doFilter(request, response);
    }

    protected boolean requiresLogout(HttpServletRequest request,
                                     HttpServletResponse response) {
        return requestMatcher.matches(request);
    }

    public String refreshToken(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {


        UserDetails userDetails = (UserDetails)authentication.getPrincipal();

        String newToken =tokenUtils.buildToken(userDetails.getUsername());
        jsonResult jsonResult = new jsonResult(true, "refresh token success.",newToken);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(this.objectMapper.writeValueAsString(jsonResult));
        response.getWriter().flush();

        return newToken;
    }


}
