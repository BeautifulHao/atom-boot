package com.atom.smart.config;


import com.atom.smart.support.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author BeautifulHao
 * @description
 * @create 2018-11-04 20:12
 **/

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(JwtProperties.class)
@AutoConfigureAfter(AtomConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private SysUserDetailsService sysUserDetailsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and()
                .authorizeRequests()
                //.antMatchers("/api/**").permitAll()
                .antMatchers("/auth/**").permitAll()
                // swagger start
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/configuration/**").permitAll()
                //swagger end
                .antMatchers("/druid/**").hasAuthority("sys-datasource-admin")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin().and()
                .logout().and()
                .httpBasic();

        http.headers().cacheControl();
        //登录过滤器
        http.addFilterBefore(getJwtLoginFilter(),UsernamePasswordAuthenticationFilter.class);
        //api token验证过滤器
        http.addFilterBefore(getJwtAuthenticationTokenFilter(), JwtLoginFilter.class);
        //注销过滤器
        http.addFilterAfter(getJwtLogoutFilter(), JwtAuthenticationTokenFilter.class);
        //刷新api token过滤器
        http.addFilterAfter(getJwtAuthenticationTokenRefreshFilter(), JwtAuthenticationTokenFilter.class);

        AntPathRequestMatcher antJwtRequestMatcher = new AntPathRequestMatcher("/api/**");
        AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher("/**");

        //对认证失败的处理，其中只处理/api/部分，其余留给security默认执行
        http.exceptionHandling().defaultAuthenticationEntryPointFor(new JwtAuthenticationEntryPoint(this.objectMapper),antJwtRequestMatcher);
        http.exceptionHandling().defaultAuthenticationEntryPointFor(new LoginUrlAuthenticationEntryPoint("/login"),antPathRequestMatcher);
        http.exceptionHandling().defaultAccessDeniedHandlerFor( new JwtAccessDeniedHandler(this.objectMapper),antJwtRequestMatcher);
        http.exceptionHandling().defaultAccessDeniedHandlerFor(new AccessDeniedHandlerImpl(),antPathRequestMatcher);
    }

    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.setAllowedMethods(Arrays.asList("GET","POST","DELETE","OPTIONS","PUT"));
        configuration.addAllowedHeader("*");
        configuration.addExposedHeader("Authorization");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(sysUserDetailsService).passwordEncoder(passwordEncoder());
        auth.authenticationProvider(getJwtAuthenticationProvider());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenUtils getTokenUtils(){
        TokenUtils tokenUtils = new TokenUtils();
        tokenUtils.setJwtProperties(jwtProperties);
        return tokenUtils;
    }

    @Bean
    public AuthResultUtils getAuthResultUtils(){
        return new AuthResultUtils();
    }

    @Bean
    public JwtLoginFilter getJwtLoginFilter() throws Exception {
        return new JwtLoginFilter(new JwtAuthenticationSuccessHandler(objectMapper,getTokenUtils()),
                new JwtAuthenticationFailureHandler(objectMapper),
                authenticationManager());
    }

    @Bean
    public JwtLogoutFilter getJwtLogoutFilter() {
        return new JwtLogoutFilter();
    }

    @Bean
    public JwtAuthenticationTokenFilter getJwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    public JwtAuthenticationProvider getJwtAuthenticationProvider(){
        return new JwtAuthenticationProvider(passwordEncoder(),sysUserDetailsService);
    }

    @Bean
    public JwtAuthenticationTokenRefreshFilter getJwtAuthenticationTokenRefreshFilter(){
        return new JwtAuthenticationTokenRefreshFilter();
    }
}
