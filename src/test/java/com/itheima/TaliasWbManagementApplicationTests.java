package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/*@SpringBootTest*/
class TaliasWbManagementApplicationTests {

    @Test
    public void testGenJwt(){//jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","tom");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"itheima")//签名算法和密钥
                .setClaims(claims)//自定义内容
                .setExpiration(new Date(System.currentTimeMillis()+3600*1000))//有效期
                .compact();
        System.out.println(jwt);
    }

    @Test
    public void testParseJwt(){
        Claims claims = Jwts.parser()
                .setSigningKey("itheima")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTY4MDU5MDg1M30.fpFlwk_pKID98prSjtqggkrHwV-FrFT8xmI28rny5rg")
                .getBody();
        System.out.println(claims);
    }

}
