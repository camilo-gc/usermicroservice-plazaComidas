package com.pragma.powerup.usermicroservice.configuration.security.jwt;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.PrincipalUser;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.JwtResponseDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.apachecommons.CommonsLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@CommonsLog
public class JwtProvider {
    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication) {
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        log.info("principalUser: " + principalUser.toString());
        List<String> roles = principalUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return Jwts.builder()
                .setSubject(principalUser.getUsername())
                .claim("roles", roles)
                .claim("id", principalUser.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 180))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    public String getUserNameFromToken(String token) {
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody().getSubject();
    }

    public String getIdFromToken(String token) {
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody().get("id").toString();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("token mal formado");
        } catch (UnsupportedJwtException e) {
            logger.error("token no soportado");
        } catch (ExpiredJwtException e) {
            logger.error("token expirado");
        } catch (IllegalArgumentException e) {
            logger.error("token vacío");
        } catch (SignatureException e) {
            logger.error("fail en la firma");
        }
        return false;
    }

    public String refreshToken(JwtResponseDto jwtResponseDto) throws ParseException {
        try {
            Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(jwtResponseDto.getToken());
        } catch (ExpiredJwtException e) {
            JWT jwt = JWTParser.parse(jwtResponseDto.getToken());
            JWTClaimsSet claims = jwt.getJWTClaimsSet();
            String userName = claims.getSubject();
            List<String> roles = claims.getStringListClaim("roles");
            String id = claims.getStringClaim("id");

            return Jwts.builder()
                    .setSubject(userName)
                    .claim("roles", roles)
                    .claim("id", id)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime() + expiration))
                    .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                    .compact();
        }
        return null;
    }

}
