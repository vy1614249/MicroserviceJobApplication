package com.vikash.gateway.service;

import com.vikash.gateway.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthentication extends
        AbstractGatewayFilterFactory<JWTAuthentication.Config> {

    public JWTAuthentication() {
        super(Config.class); // tells Spring which config class to bind
        System.out.println("JWT Filter bean created.");
    }



    @Override
    public GatewayFilter apply(JWTAuthentication.Config config) {
        return (exchange,chain)->{
            String path = exchange.getRequest().getURI().getPath();
            System.out.println("Incoming request path: " + path);
            // allow /auth/** without token
            if (path.startsWith("/auth/")) {
                System.out.println("Skipping JWT for auth path");
                return chain.filter(exchange);

            }

            String auth=exchange.getRequest()
                    .getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            System.out.println("Authorization header: " + auth);
            if(auth==null || !auth.startsWith("Bearer ")){
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
            String token=auth.substring(7);
            try {
                Claims claims= Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(config.getSecret().getBytes()))
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                ServerHttpRequest modifiedRequest = exchange.getRequest()
                        .mutate()
                        .header("X-User-Email", claims.getSubject())
                        .header("X-User-Role",claims.get("roles").toString())
                        .build();

                return chain.filter(exchange.mutate().request(modifiedRequest).build());
            } catch (Exception e) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        };
    }
    @Data
    public static class Config{
        private String secret;
    }
}
