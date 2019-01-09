package com.atom.smart.support;
import org.springframework.security.core.AuthenticationException;
/**
 * @author BeautifulHao
 * @description
 * @create 2018-11-09 11:07
 **/
public class InvalidTokenException extends AuthenticationException {

    public InvalidTokenException(String msg) {
        super(msg);
    }

    public InvalidTokenException(String msg, Throwable t) {
        super(msg, t);
    }
}
