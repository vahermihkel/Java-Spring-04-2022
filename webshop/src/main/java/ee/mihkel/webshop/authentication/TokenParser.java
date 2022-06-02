package ee.mihkel.webshop.authentication;

import ee.mihkel.webshop.repository.PersonRepository;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Log4j2
public class TokenParser extends BasicAuthenticationFilter {
    // @Value ei saa
    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    public TokenParser(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    // Basic - username + password
    // Bearer - token, mille sees on mitmeid väärtusi

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            log.info(token);
            token = token.replace("Bearer ", "");

            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(key)
                        .parseClaimsJws(token)
                        .getBody();

                String email = claims.getSubject();
                String role = claims.getIssuer();

                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
                List<GrantedAuthority> roles = new ArrayList<>(Collections.singletonList(grantedAuthority));

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        email,
                        null,
                        roles
                );

                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (ExpiredJwtException |
                    MalformedJwtException |
                    UnsupportedJwtException |
                    SignatureException |
                    IllegalArgumentException e) {
                log.error(e.getMessage());
            }
        }

        super.doFilterInternal(request, response, chain);
    }
}
