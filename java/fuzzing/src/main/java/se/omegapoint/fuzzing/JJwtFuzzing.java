package se.omegapoint.fuzzing;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import java.security.Key;


public class JJwtFuzzing {

    public static void fuzzerTestOneInput(com.code_intelligence.jazzer.api.FuzzedDataProvider data) {
        String input = data.consumeRemainingAsString();
        if (input == null) {
            return;
        }
        try {
            parse(input);
        } catch (/*ExpiredJwtException |
                 UnsupportedJwtException |
                 MalformedJwtException |
                 SignatureException |
                 IllegalArgumentException */
                 JwtException
                 | java.lang.IllegalArgumentException // | java.lang.ClassCastException
                 ignored) {

        }
    }

    public static Jws<Claims> parse(String jws) {
        return parse(jws, Keys.secretKeyFor(SignatureAlgorithm.HS256));
    }

    public static Jws<Claims> parse(String jws, Key key) {
        JwtParser parser = Jwts.parserBuilder().setSigningKey(key).build();
        Jws<Claims> claims = parser.parseClaimsJws(jws);

        System.out.println(claims.getBody());
        return claims;
    }


    public static void main(String [] args) {
        /* New random key */
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        String jws = Jwts.builder().setSubject("Joe").signWith(key).compact();
        System.out.println(jws);

        Jws<Claims> claims = parse(jws, key);

    }

}
