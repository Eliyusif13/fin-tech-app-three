package com.sadiqov.tech_app_three.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtUtil {

    @Autowired
    Logger logger;

    @Value("${SECRET_KEY}")
    String secretKey;

    // UserDetails obyektindən istifadə edərək JWT token yaradır
    public String createToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("test", "test"); // Test üçün əlavə edilmiş məlumat
        return generateToken(claims, userDetails.getUsername());
    }

    // Verilən məlumatlar və istifadəçi adı ilə JWT token yaradır
    private String generateToken(Map<String, Object> claims, String username) {
        logger.info("Creating token .............");

        return Jwts.builder()
                .setClaims(claims) // Tokenin içində saxlanacaq əlavə məlumatlar
                .setSubject(username) // Tokenin əsas subyekti (istifadəçi adı)
                .setIssuedAt(new Date(System.currentTimeMillis())) // Tokenin yaradılma vaxtı
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15)) // 5 dəqiqə sonra vaxtı bitəcək
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256) // HS256 alqoritmi ilə imzalanır
                .compact(); // Tokeni string kimi qaytarır
    }

    // Tokenin içindəki bütün məlumatları çıxarır
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes()) // Tokenin imzasını yoxlamaq üçün açardan istifadə edir
                .parseClaimsJws(token)
                .getBody(); // Tokenin əsas hissəsini (payload) qaytarır
    }

    // Tokenin içində müəyyən bir məlumatı çıxarmaq üçün istifadə edilir
    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Tokenin içindəki istifadəçi adını çıxarır
    public String getUserPin(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    // Tokenin bitmə vaxtını qaytarır
    public Date getDate(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    // Tokenin etibarlı olub-olmadığını yoxlayır
    public boolean validateToken(String token, UserDetails userDetails) {
        return (!expiredToken(token) && (getUserPin(token).equals(userDetails.getUsername())));
    }

    // Tokenin müddətinin bitib-bitmədiyini yoxlayır
    // Əgər tokenin bitmə tarixi indiki tarixdən köhnədirsə, etibarsızdır
    private boolean expiredToken(String token) {
        return getDate(token).before(new Date());
    }
}
