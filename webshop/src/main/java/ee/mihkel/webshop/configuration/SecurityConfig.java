package ee.mihkel.webshop.configuration;

import ee.mihkel.webshop.authentication.TokenParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${token.key}")
    private String key;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        TokenParser tokenParser = new TokenParser(authenticationManager());
        tokenParser.setKey(key);

        http
                .cors().and().headers().xssProtection().disable().and()
                .csrf().disable()
                .addFilter(tokenParser)
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/products").permitAll()
                .antMatchers(HttpMethod.GET,"/products/*").permitAll()
                .antMatchers(HttpMethod.POST,"/login").permitAll()
                .antMatchers(HttpMethod.POST, "/signup").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui/**", "/v3/api-docs/**").hasRole("SUPER_ADMIN")
                .antMatchers( "person/**").hasRole("SUPER_ADMIN")
                .antMatchers( "add-admin/**").hasRole("SUPER_ADMIN")
                .antMatchers( "add-super-admin/**").hasRole("SUPER_ADMIN")
                .antMatchers( "get-admins").hasRole("SUPER_ADMIN")
                .antMatchers( "get-super-admins").hasRole("SUPER_ADMIN")
                .antMatchers( "delete-role/**").hasRole("SUPER_ADMIN")
                .antMatchers( "category").hasAnyRole("ADMIN", "SUPER_ADMIN")
                .antMatchers( "sub-category").hasAnyRole("ADMIN", "SUPER_ADMIN")
                .antMatchers(HttpMethod.POST, "products").hasAnyRole("ADMIN", "SUPER_ADMIN")
                .antMatchers(HttpMethod.DELETE, "products").hasAnyRole("ADMIN", "SUPER_ADMIN")
                .antMatchers(HttpMethod.PUT, "products").hasAnyRole("ADMIN", "SUPER_ADMIN")
                .antMatchers(HttpMethod.GET, "/swagger-ui/**", "/v3/api-docs/**").hasRole("SUPER_ADMIN")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
