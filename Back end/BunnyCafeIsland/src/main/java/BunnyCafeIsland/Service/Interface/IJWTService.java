package BunnyCafeIsland.Service.Interface;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface IJWTService {
    String getUserEmailFromToken(String token);
    Date getExpirationDateFromToken(String token);
    <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver);
    String createTokenForUser(UserDetails userDetails);
    boolean validateToken(String token, UserDetails userDetails);
}
