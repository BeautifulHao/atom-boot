package com.atom.smart.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
 * @create 2018-11-09 10:44
 **/
public class JwtLogoutFilter extends GenericFilterBean {


    private RequestMatcher logoutRequestMatcher;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public JwtLogoutFilter() {
        this.logoutRequestMatcher = new AntPathRequestMatcher("/api/logout");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (requiresLogout(request, response)) {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            if (logger.isDebugEnabled()) {
                logger.debug("Logging out user '" + auth
                        + "' and transferring to logout destination");
            }

            //更新注销的token池，有效期为token有效期，redis自动删除失效token
            if(auth instanceof JwtUsernamePasswordAuthenticationToken){
                JwtUsernamePasswordAuthenticationToken jwtUsernamePasswordAuthenticationToken = (JwtUsernamePasswordAuthenticationToken)auth;
                String token = jwtUsernamePasswordAuthenticationToken.getToken();
                Date expire = tokenUtils.getExpire(token);
                long expireMis= expire !=null? expire.getTime() - System.currentTimeMillis() : 0l;

                if(expireMis > 0l){
                    redisTemplate.opsForValue().set(token, System.currentTimeMillis(),expireMis, TimeUnit.MILLISECONDS);
                }

            }

            logoutSuccess(request, response, auth);

            SecurityContext context = SecurityContextHolder.getContext();

            context.setAuthentication(null);

            return;
        }

        chain.doFilter(request, response);
    }

    protected boolean requiresLogout(HttpServletRequest request,
                                     HttpServletResponse response) {
        return logoutRequestMatcher.matches(request);
    }

    protected void logoutSuccess(HttpServletRequest request,HttpServletResponse response ,Authentication auth) throws IOException {
        jsonResult jsonResult = new jsonResult(true, "注销成功.", "");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(this.objectMapper.writeValueAsString(jsonResult));
        response.getWriter().flush();
    }
}
