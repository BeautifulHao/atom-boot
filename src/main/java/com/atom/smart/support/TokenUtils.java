package com.atom.smart.support;

import com.atom.smart.support.JwtProperties;
import com.atom.smart.support.SysUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.DefaultHeader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;

/**
 * @author BeautifulHao
 * @description
 * @create 2018-11-07 14:41
 **/
public class TokenUtils {

    private static final Log logger = LogFactory.getLog(TokenUtils.class);

    private JwtProperties jwtProperties;

    public void setJwtProperties(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public JwtProperties getJwtProperties() {
        return jwtProperties;
    }

    public Claims getClaims(String token){
        try
        {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(jwtProperties.getSecret()))
                    .parseClaimsJws(token)
                    .getBody();

            return claims;
        }
        catch(Exception ex)
        {
            logger.info("token parser error :"+ex.getMessage());
            return null;
        }
    }

    public String getUserNameFromToken(String token) {
        Claims claims = getClaims(token);
        return claims ==null?null: claims.getSubject();
    }

    public Date getExpire(String token){
        Claims claims = getClaims(token);
        return claims ==null?null:claims.getExpiration();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {

        Claims claims = getClaims(token);

        if(claims ==null){
            return false;
        }

        Date created = claims.getExpiration();

        return new Date().getTime() < created.getTime()
                && userDetails.isEnabled()
                && userDetails.isAccountNonExpired()
                && userDetails.isCredentialsNonExpired()
                && userDetails.isAccountNonLocked();
    }

    public String buildToken(String user){

        Claims claims = new DefaultClaims();
        claims.setExpiration(new Date(System.currentTimeMillis()+Long.valueOf(jwtProperties.getExpiration())));
        claims.setSubject(user);
        claims.setAudience("atom-boot");

        String token= Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                .compact();

        return token;
    }

}
