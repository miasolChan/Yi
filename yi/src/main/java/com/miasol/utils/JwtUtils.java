package com.miasol.utils;

import com.miasol.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class JwtUtils {
    private String secret = "f4e2e52034348f86b67cde581c0f9eb5";
    private long expire = 1000 * 60 * 60 * 24 * 7;
    private String subject = "RookieLi";

    /**
     * 生成jwt token
     */
    public String generateToken(User user) {
        if(user == null) {
            return null;
        }

        String token = Jwts.builder()
                .setSubject(subject)
                .claim("id", user.getId())
                .claim("mobile", user.getMobile())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
        return token;
    }

    public Claims getClaimByToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            return null;
        }
    }

    /**
     * token是否过期
     * @return  true：过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }
}
