package com.sparklecow.cowradio.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Jwts;

@Component
public class JwtUtils {

    @Value("${spring.application.security.jwt.secret}")
    private String secret;
    @Value("${spring.application.security.jwt.expiration}")
    private long expiration;

    //Create a token with the user's details and an optional map of claims
    private String createToken(UserDetails user){
        return this.createToken(user, Map.of());
    }

    private String createToken(UserDetails user, Map<String, Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(this.signKey())
                .compact();
    }

    //Token validations
    public boolean isTokenValid(String token, UserDetails user) {
        return extractUsername(token).equals(user.getUsername()) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    //This method extract a specific claim from the token according to the parameter provided by the function (<T>)
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        return claimsResolver.apply(this.extractAllClaims(token));
    }

    public Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(this.signKey())
                .build().
                parseClaimsJwt(token).getBody();
    }

    public String extractUsername(String token){
        return this.extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return this.extractClaim(token, Claims::getExpiration);
    }

    //Key for signing the token
    public Key signKey(){
        byte[] bytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(bytes);
    }
}
