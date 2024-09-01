package com.Aaron.MFM.common.utils;

import com.Aaron.MFM.common.exception.MFMException;
import com.Aaron.MFM.common.result.ResultCodeEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JWTutils {

    // token过期时间
    private static long tokenExpiresTime = 60 * 60 * 24 * 30 * 1000L;

    private static SecretKey tokenSecretKey = Keys.hmacShaKeyFor("com.Aaron.www-com.Aaron.www-com.Aaron.www-".getBytes());

    public static String createToken(Long userId,String role){
        String token = Jwts.builder()
                // 设置主题
                .setSubject("USER_INFO")
                // 设置过期时间
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiresTime))
                // 设置用户id
                .claim("userId", userId)
                // 设置角色
                .claim("role", role)
                // 设置签名
                .signWith(tokenSecretKey)
                // 生成token
                .compact();
        return token;
    }

    public static Claims parseToken(String token){
        if(token == null){
            throw new MFMException(ResultCodeEnum.TOKRN_EMPTY);
        }
        try{
            JwtParser parser = Jwts.parserBuilder().setSigningKey(tokenSecretKey).build();
            Jws<Claims> claimsJws = parser.parseClaimsJws(token);
            return claimsJws.getBody();
        }catch (ExpiredJwtException e){
            throw new MFMException(ResultCodeEnum.TOKEN_EXPIRED);
        }catch (JwtException e){
            throw new MFMException(ResultCodeEnum.TOKEN_INVALID);
        }
    }
}
