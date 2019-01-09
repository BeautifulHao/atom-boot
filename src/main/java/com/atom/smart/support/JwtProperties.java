package com.atom.smart.support;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author BeautifulHao
 * @description
 * @create 2018-11-07 15:11
 **/
@ConfigurationProperties(prefix = "spring.jwt")
public class JwtProperties {

    private String secret;

    private String expiration;

    private String tokenHeader;


    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getTokenHeader() {
        return tokenHeader;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    @Override
    public String toString() {
        return "JwtProperties{" +
                "secret='" + secret + '\'' +
                ", expiration='" + expiration + '\'' +
                ", tokenHeader='" + tokenHeader + '\'' +
                '}';
    }
}
