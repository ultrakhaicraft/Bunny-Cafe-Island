package BunnyCafeIsland.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JwtService {


    //TODO: give secret key
    private static final String SECRET_KEY="/M+4vL/U/oHiB/nvApMqqWtMaoRPddB49R/yI9wkq7VpuaQwCb8141KkRiyAmrwA";

    public String getUserEmailFromToken(String token){
        return getClaimFromToken(token,Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token,Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public<T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims=getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey()) //SignInKey is our secret key
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey(){
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String createTokenForUser(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }

    private String generateToken(
            Map<String, Object> extraClaims, UserDetails userDetails
    ){

        Date currentDateTime = Date.from(Instant.now());


        Date expirationDateTime = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(currentDateTime)
                .setExpiration(expirationDateTime)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails){
        final String userEmail=getUserEmailFromToken(token);
        return (userEmail.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
