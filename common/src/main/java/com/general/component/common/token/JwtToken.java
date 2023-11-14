package com.general.component.common.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.general.component.common.jackson.JacksonSupport;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: token
 * @author: littlesnail
 * @create: 2023−02-27 3:04 PM
 */
public class JwtToken {
    /**
     * JWT密钥
     */
    private String jwtSecret;
    /**
     * 密钥生成者
     */
    private String jwtOperator;
    /**
     * JWT主题
     */
    private String jwtSubject;

    /**
     * JWT_CLAIM_KEY
     */
    private String jwtClaimKey;

    /**
     * JWT过期时间(小时)
     */
    private static final int DEFAULT_EXPIRE_HOUR = 2;

    /**
     * 设置头部信息
     */
    private Map<String, Object> headMap;

    /**
     * 签名 Signature
     */
    private Algorithm algorithm;


    /**
     * 构造方法
     *
     * @param jwtSecret
     * @param jwtOperator
     * @param jwtSubject
     * @param jwtClaimKey
     */
    public JwtToken(String jwtSecret, String jwtOperator, String jwtSubject,
                    String jwtClaimKey) {
        this.jwtSecret = jwtSecret;
        this.jwtOperator = jwtOperator;
        this.jwtSubject = jwtSubject;
        this.jwtClaimKey = jwtClaimKey;
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        this.headMap = map;
        this.algorithm = Algorithm.HMAC256(jwtSecret);
    }

    public JwtToken(String jwtSecret, String jwtOperator, String jwtSubject, String jwtClaimKey, Map<String, Object> headMap, Algorithm algorithm) {
        this.jwtSecret = jwtSecret;
        this.jwtOperator = jwtOperator;
        this.jwtSubject = jwtSubject;
        this.jwtClaimKey = jwtClaimKey;
        this.headMap = headMap;
        this.algorithm = algorithm;
    }


    /**
     * 创建token
     *
     * @param expireDate 过期时间
     * @return
     */
    public String createToken(Date expireDate) {
        String token = "";
        try {
            token = JWT.create()
                    /* 设置头部信息 Header */
                    .withHeader(this.headMap)
                    /* 设置 载荷 Payload */
                    // 签名是有谁生成 例如 服务器
                    .withIssuer(this.jwtOperator)
                    // 签名的主题
                    .withSubject(this.jwtSubject)
                    // .withNotBefore(new Date())//定义在什么时间之前，该jwt都是不可用的.
                    // .withAudience("APP")// 签名的观众 也可以理解谁接受签名的
                    // 生成签名的时间
                    .withIssuedAt(new Date())
                    // 签名过期的时间
                    .withExpiresAt(expireDate)
                    /* 签名 Signature */
                    .sign(this.algorithm);
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
        }
        return token;
    }

    /**
     * createTokenWithClaim
     *
     * @param obj        token 中 自定义属性
     * @param expireDate 过期时间
     * @return
     */
    public String createTokenWithClaim(Object obj, Date expireDate) throws Exception {
        try {
            String json = JacksonSupport.objToJsonStr(obj);
            String token = JWT.create()
                    /* 设置头部信息 Header */
                    .withHeader(this.headMap)
                    /* 设置 载荷 Payload */
                    .withClaim(this.jwtClaimKey, json)
                    // 签名是有谁生成 例如 服务器
                    .withIssuer(this.jwtOperator)
                    // 签名的主题
                    .withSubject(this.jwtSubject)
                    // .withNotBefore(new Date())//定义在什么时间之前，该jwt都是不可用的.
                    // .withAudience("APP")// 签名的观众 也可以理解谁接受签名的
                    // 生成签名的时间
                    .withIssuedAt(new Date())
                    // 签名过期的时间
                    .withExpiresAt(expireDate)
                    /* 签名 Signature */
                    .sign(this.algorithm);
            return token;
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * createTokenWithClaim
     *
     * @param obj token 中 自定义属性
     * @return
     */
    public String createTokenWithClaim(Object obj) throws Exception {
        try {
            String json = JacksonSupport.objToJsonStr(obj);
            // 默认2小过期
            Date expireDate = new Date(System.currentTimeMillis() + 1000L * 60L * 60 * 2);
            String token = JWT.create()
                    /* 设置头部信息 Header */
                    .withHeader(this.headMap)
                    /* 设置 载荷 Payload */
                    .withClaim(this.jwtClaimKey, json)
                    // 签名是有谁生成 例如 服务器
                    .withIssuer(this.jwtOperator)
                    // 签名的主题
                    .withSubject(this.jwtSubject)
                    // .withNotBefore(new Date())//定义在什么时间之前，该jwt都是不可用的.
                    // .withAudience("APP")// 签名的观众 也可以理解谁接受签名的
                    // 生成签名的时间
                    .withIssuedAt(new Date())
                    // 签名过期的时间
                    .withExpiresAt(expireDate)
                    /* 签名 Signature */
                    .sign(this.algorithm);
            return token;
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * verifyToken 解析token
     *
     * @param token
     * @return
     */
    public DecodedJWT verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.jwtSecret);
            // Reusable verifier
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(this.jwtOperator).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt;
        } catch (JWTVerificationException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
