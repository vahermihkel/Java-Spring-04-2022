package ee.mihkel.webshop.controller;

import ee.mihkel.webshop.authentication.TokenGenerator;
import ee.mihkel.webshop.controller.exception.EmailExistsException;
import ee.mihkel.webshop.controller.exception.InvalidPasswordException;
import ee.mihkel.webshop.controller.exception.PersonExistsException;
import ee.mihkel.webshop.controller.exception.PersonNotFoundException;
import ee.mihkel.webshop.model.database.Person;
import ee.mihkel.webshop.model.request.input.LoginData;
import ee.mihkel.webshop.model.request.output.AuthData;
import ee.mihkel.webshop.repository.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ValidationException;
import java.sql.SQLException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Log4j2
public class AuthenticationController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    TokenGenerator tokenGenerator;

    @PostMapping("signup")
    public ResponseEntity<Boolean> signup(@RequestBody Person person) throws PersonExistsException, EmailExistsException, Exception {
//        person.setPassword(); hashimine
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        System.out.println("ERINEV:");
//        System.out.println(encoder);
//        System.out.println("ÜKS JA SAMA:");
//        System.out.println(passwordEncoder);
        if (personRepository.findById(person.getPersonCode()).isPresent()) {
            throw new PersonExistsException();
        }
        String hashedPassword = passwordEncoder.encode(person.getPassword());
        person.setPassword(hashedPassword);
        try {
            personRepository.save(person);
            return ResponseEntity.ok().body(true);
        } catch (RuntimeException e) {
            Throwable rootCause = com.google.common.base.Throwables.getRootCause(e);
            if (rootCause instanceof SQLException) {
//                log.error(rootCause.getMessage());
                throw new EmailExistsException(rootCause.getMessage());
            } else {
                throw new ValidationException(rootCause.getMessage());
            }
        }
    }

    @PostMapping("login")      // {email: "m@m.com", password: "123"}
    public ResponseEntity<AuthData> login(@RequestBody LoginData loginData) throws PersonNotFoundException, InvalidPasswordException {
//        person.setPassword();
        Person person = personRepository.getByEmail(loginData.getEmail());
        if (person == null) {
            throw new PersonNotFoundException();
        }
        boolean matches = passwordEncoder.matches(loginData.getPassword(),person.getPassword());
        if (matches) {
            AuthData authData = tokenGenerator.createAuthToken(loginData.getEmail());
            return ResponseEntity.ok().body(authData);
        } else {
            throw new InvalidPasswordException();
        }
    }
}
