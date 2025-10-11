package jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: RightSquare
 * @Date: 2025/10/11 10:03
 * @Description: JwtTests
 */
public class JwtTests {

    @Test
    public void testGenerateToken() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", "admin");
        String Jwt = Jwts.builder()
                .setClaims(claims)
                .setId("1")
                .setSubject("admin")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, "qkfdsakjlfdjsakljfdslkajflkdsafsda")
                .compact();
        System.out.println(Jwt);
    }

    @Test
    public void testParseToken() {
        String token ="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTc2MDE1NDA4OCwiaWF0IjoxNzYwMTUwNDg4LCJqdGkiOiIxIiwidXNlcm5hbWUiOiJhZG1pbiJ9.Nnn5BX_d5VFQ-3uyBHfIWTl9aUAcd6J1TnL0PF2U5sQ";
        Claims claim = Jwts.parser().setSigningKey("qkfdsakjlfdjsakljfdslkajflkdsafsda")
                .parseClaimsJws(token)
                .getBody();
        Object username = claim.get("username");
        System.out.println(username);

    }
}
