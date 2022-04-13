package ee.mihkel.webshop.controller;

import ee.mihkel.webshop.model.input.EveryPayResponse;
import ee.mihkel.webshop.model.output.EveryPayData;
import ee.mihkel.webshop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.util.Date;

@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;
    // nagu PaymentService paymentService = new PaymentService()
    // aga üks mälukoht koguaeg
    // selleks et @Autowired saaks panna peab olema @Component või laadset

    @PostMapping("payment")  // localhost:8080/payment    Body    80   text
    public String getPaymentLink(@RequestBody String amount) {
        System.out.println(paymentService);
        return paymentService.getPaymentLink(amount);
    }
}
