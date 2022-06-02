package ee.mihkel.webshop.authentication;

import ee.mihkel.webshop.model.database.Person;
import ee.mihkel.webshop.model.request.output.AuthData;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenGenerator {

    @Value("${token.key}")
    private String key;

    public AuthData createAuthToken(Person person) {
        AuthData authData = new AuthData();
        Date newDate = DateUtils.addHours(new Date(), 5);

        String token = Jwts.builder()
            .signWith(SignatureAlgorithm.HS512, key)
            .setIssuer(person.getRole())
            .setSubject(person.getEmail())
            .setExpiration(newDate)
            .compact();

        authData.setToken(token);
        authData.setExpiration(newDate);
        return authData;
    }
}
