package com.llun.security;

import io.jsonwebtoken.*;
import java.util.Date;
import java.util.Properties;
import java.io.InputStream;

public class JwtUtil {
    private static String SECRET_KEY;

    static {
        try (InputStream input = JwtUtil.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            SECRET_KEY = prop.getProperty("secretKey");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 sa3at
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static Claims validateToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
}