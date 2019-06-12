package com.example.demo.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.module.models.User;

public class JwtUtil {
    private Integer timestamp = 60000;
    Algorithm algorithm = Algorithm.HMAC256("secret");

    public String generateToken(User user) {
        Long expMillis = new Date().getTime()+ timestamp;
        Date exp = new Date(expMillis);
        String jwt = JWT.create()
                .withClaim("username", user.getUsername())
                .withClaim("password", user.getPassword())
                .withExpiresAt(exp).sign(algorithm);
        return jwt;
    }

    public Object parseToken(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        Map<String,Claim> map =jwt.getClaims();
        Date time = jwt.getExpiresAt();
        Map<String,Object> mp = new HashMap<>();
        mp.put("username", map.get("username").asString());
        mp.put("password", map.get("password").asString());
        mp.put("expireDate", time.toString());
        return mp;
    }
}