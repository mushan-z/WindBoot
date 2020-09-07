package com.wind.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author zhoubin
 * @time 2020/9/7 下午 5:00
 */
@Slf4j
public class JWTUtil {

    private static final Long EXPIRE_TIME =1000*60*60L;


    public static String getToken(String userName,String password){
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(password);
        String token = JWT.create().withExpiresAt(expireDate).withClaim("userName",userName).sign(algorithm);
        log.info("userName:{} password:{} token:{}",userName,password,token);
        return token;
    }

    public static boolean verify(String token,String userName,String secret) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier jwtVerifier = JWT.require(algorithm).withClaim("userName", userName).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            String header = decodedJWT.getHeader();
            log.info("verify header:{}",header);
            String payload = decodedJWT.getPayload();
            log.info("verify payload:{}",payload);
            String signature = decodedJWT.getSignature();
            log.info("verify signature:{}",signature);
            return true;
        }catch (TokenExpiredException te) {
            log.error("token超时",te);
        }catch(SignatureVerificationException se){
            log.error("校验失败，密码错误",se);
        }catch(Exception e){
            log.error("失败",e);
        }
        return false;
    }

    public static String getUserName(String token){
        String userName = JWT.decode(token).getClaim("userName").asString();
        log.info("getUserName userName:{}",userName);
        return userName;
    }
}
