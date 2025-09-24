package com.vikash.gateway.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JWTUtils {
    String secret="This-is-my-secret-key";
//    public String generateToken(UserDetails user){
//
//        return Jwts.builder()
//                .setSubject(user.getUsername())
//                .claim("roles",user.getAuthorities()
//                        .stream().map(GrantedAuthority::getAuthority).toList())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis()+60*60*1000))
//                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
//                .compact();
//    }

    public boolean validateToken(String token,UserDetails userDetails){
        String fetchedUserName=extractUsername(token);
        return userDetails.getUsername().equals(fetchedUserName) && !isTokenExpired(token);
    }

    public String extractUsername(String token){
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenExpired(String token){
        Date expirationdate=Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expirationdate.before(new Date());
    }


}
