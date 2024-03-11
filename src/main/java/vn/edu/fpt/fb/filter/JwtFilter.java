package vn.edu.fpt.fb.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import vn.edu.fpt.fb.service.inter.HandleTokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static org.springframework.util.ObjectUtils.isEmpty;

/**
 * vn.edu.fpt.accounts.filter
 **/

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final HandleTokenService handleTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        log.info("URL: {}", request.getRequestURL().toString());
        log.info("Token: {}",  request.getHeader(HttpHeaders.AUTHORIZATION));
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        final String token = header.split(" ")[1].trim();
        Optional<Authentication> authenticationFromToken = handleTokenService.getAuthenticationFromToken(token);

        if(authenticationFromToken.isEmpty()){
            chain.doFilter(request, response);
            return;
        }
        Authentication authentication = authenticationFromToken.get();

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

}
