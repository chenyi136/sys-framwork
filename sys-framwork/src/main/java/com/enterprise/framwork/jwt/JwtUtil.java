package com.enterprise.framwork.jwt;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.enterprise.common.exception.AuthException;
import java.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
//@EnableConfigurationProperties({JwtProperties.class})
@Slf4j
public class JwtUtil {

    @Autowired
    private JwtProperties jwtProperties;


    public String encode(String name) {
        Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecret());
        String token = JWT.create()
            //设置过期时间为一个小时
            .withExpiresAt(new Date(System.currentTimeMillis() + 60000))
            //设置负载
            .withClaim("name", name)
            .sign(algorithm);
        return token;
    }

    /**
     * Description: 解密jwt
     *
     * @param token  token
     * @param secret secret
     */
    public  Map<String, Claim> decode(String token) throws AuthException {
        if (token == null || token.length() == 0) {
            throw new AuthException("token为空:" + token);
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecret());
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            return decodedJWT.getClaims();
        }catch (TokenExpiredException e){
            throw new AuthException("token已过期");
        } catch (Exception e){
            throw new AuthException("token验证失败");
        }
    }


}