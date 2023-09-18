package vn.edu.fpt.horo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import vn.edu.fpt.horo.constant.ResponseStatusEnum;
import vn.edu.fpt.horo.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Objects;
import java.util.Optional;

/**
 * vn.edu.fpt.accounts.utils
 **/

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public class AuditorUtils {

    private static String SECRET_KEY;
    @Value("${app.security.secret-key}")
    private void setSecretKey(String secretKey){
        SECRET_KEY = secretKey;
    }
    public static String getUserIdInToken(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }
    public static Optional<String> getUserId() {
        HttpServletRequest request = getCurrentRequest();
        try {
            String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
            if(Objects.nonNull(authorization) && authorization.startsWith("Bearer ")){
                return Optional.of(getClaimsFromToken(authorization).get(Claims.ID, String.class));
            }else{
                return Optional.empty();
            }
        }catch (Exception e){
            return Optional.empty();
        }
    }
    public static Key getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static JwtParser getJwtParser(){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build();
    }

    public static Claims getClaimsFromToken(String token) {
        try {
            Jws<Claims> claimsJws = getJwtParser().parseClaimsJws(token);
            return claimsJws.getBody();
        }catch (Exception ex){
            throw new BusinessException(ResponseStatusEnum.UNAUTHORIZED, "Token invalid: "+ ex.getMessage());
        }
    }
    public static HttpServletRequest getCurrentRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes)requestAttributes).getRequest();
        } else {
            return null;
        }
    }
}
