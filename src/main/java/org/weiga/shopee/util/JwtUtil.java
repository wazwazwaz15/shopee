package org.weiga.shopee.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.weiga.shopee.model.UserInfo;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;


public class JwtUtil {

    private static final KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
    private static final PrivateKey privateKey = keyPair.getPrivate();

    private static final PublicKey publicKey = keyPair.getPublic();


    private final static String issuer = "BoWei";

    private static final long expiredTime = 86400000L;


    public static String generateToken(Authentication authentication) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiredTime);

        UserInfo userInfo = (UserInfo) authentication.getPrincipal();

        Claims claims = Jwts.claims();
        claims.setSubject(userInfo.getUsername());
        claims.setAudience(userInfo.getUsername());
        claims.setIssuer(issuer);
        claims.setExpiration(expiryDate);
        claims.setIssuedAt(now);

        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
        return token;


    }


    public static String parseToken(String token){
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build();
        Claims claims = parser.parseClaimsJws(token)
                .getBody();
        String userName = claims.getSubject();
        return userName;


    }


}
