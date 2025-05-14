package emt.lab.service.domain.impl;

import emt.lab.model.domain.AuthLog;
import emt.lab.service.domain.AuthLogService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtServiceImpl {

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey secretKey;

    private final AuthLogService authLogService;

    public JwtServiceImpl(AuthLogService authLogService) {
        this.authLogService = authLogService;
    }

    @PostConstruct
    public void init() {
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        secretKey = Keys.hmacShaKeyFor(decodedKey);
    }

    public String generateToken(UserDetails userDetails) {
        String username = userDetails.getUsername();
        Date expiration = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24);
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiration) // 24h
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        AuthLog authLog = new AuthLog(username, token, expiration);
        authLogService.save(authLog);
        return token;
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername()) &&
                !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
}
