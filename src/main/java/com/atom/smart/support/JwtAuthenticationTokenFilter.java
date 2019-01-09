package com.atom.smart.support;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *
 * 对/api/请求进行拦截，如果有头信息进行认证信息填充
 *
 * @author BeautifulHao
 * @description
 * @create 2018-11-07 14:40
 **/
public class JwtAuthenticationTokenFilter extends GenericFilterBean {

    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private AuthResultUtils authResultUtils;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private RequestMatcher requestMatcher;

    public JwtAuthenticationTokenFilter() {
        setRequestMatcher(new AntPathRequestMatcher("/api/**"));
    }

    public void setRequestMatcher(RequestMatcher requestMatcher) {
        this.requestMatcher = requestMatcher;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        //符合条件的请求，强行验证
        if(this.requestMatcher.matches(httpRequest)) {

            try {
                String authHeader = httpRequest.getHeader(this.tokenUtils.getJwtProperties().getTokenHeader());

                if (authHeader != null && authHeader.startsWith("Bearer ")) {

                    final String authToken = authHeader.substring(7);
                    String username = this.tokenUtils.getUserNameFromToken(authToken);
                    if (StringUtils.isNotEmpty(username)) {

                        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                        if (this.tokenUtils.validateToken(authToken, userDetails)) {

                            //匹配token池，是否存在有效内的失效token（有效期内的注销token）
                            if(redisTemplate.opsForValue().get(authToken) !=null){
                                throw new InvalidTokenException(" Uneffective token ,please re-login again.");
                            }

                            JwtUsernamePasswordAuthenticationToken authentication = new JwtUsernamePasswordAuthenticationToken(userDetails,
                                    null,
                                    userDetails.getAuthorities());
                            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                            authentication.setToken(authToken);
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }

                        //处理认证失败异常，直接返回
                        if (authResultUtils.HandleAuthFromUserDetails(response, userDetails)) {
                            return;
                        }

                    }
                    else{
                        throw new InvalidTokenException("token valid error:auth-Header is invalid.");
                    }
                }
                else {
                    throw new InvalidTokenException("token valid error:api request need auth-Header");
                }
            }
            catch (Exception e)
            {
                authResultUtils.HandleAuthException(response,e);
                return;
            }
        }

        chain.doFilter(request, response);
    }

}