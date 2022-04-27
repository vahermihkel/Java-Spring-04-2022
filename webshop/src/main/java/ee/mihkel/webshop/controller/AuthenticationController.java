package ee.mihkel.webshop.controller;

import ee.mihkel.webshop.authentication.TokenGenerator;
import ee.mihkel.webshop.model.database.Person;
import ee.mihkel.webshop.model.request.input.LoginData;
import ee.mihkel.webshop.model.request.output.AuthData;
import ee.mihkel.webshop.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    TokenGenerator tokenGenerator;

    @PostMapping("signup")
    public ResponseEntity<Boolean> signup(@RequestBody Person person) {
//        person.setPassword(); hashimine
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        System.out.println("ERINEV:");
//        System.out.println(encoder);
//        System.out.println("ÜKS JA SAMA:");
//        System.out.println(passwordEncoder);
        String hashedPassword = passwordEncoder.encode(person.getPassword());
        person.setPassword(hashedPassword);
        personRepository.save(person);
        return ResponseEntity.ok().body(true);
    }

    @PostMapping("login")      // {email: "m@m.com", password: "123"}
    public ResponseEntity<AuthData> login(@RequestBody LoginData loginData) {
//        person.setPassword();
        Person person = personRepository.getByEmail(loginData.getEmail());
        boolean matches = passwordEncoder.matches(loginData.getPassword(),person.getPassword());
        if (matches) {
            AuthData authData = tokenGenerator.createAuthToken(loginData.getEmail());
            return ResponseEntity.ok().body(authData);
        }
        return ResponseEntity.badRequest().body(null);
    }
}
