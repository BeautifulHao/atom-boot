package com.atom.smart;

import com.atom.smart.support.JwtProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author BeautifulHao
 * @description
 * @create 2018-11-07 15:29
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UtlisTests {

    @Autowired
    private JwtProperties jwtProperties;

    @Test
    public void testJwtPro(){
        System.out.println(jwtProperties.toString());
    }
}
