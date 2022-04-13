package ee.mihkel.webshop.controller;

import ee.mihkel.webshop.model.output.EveryPayData;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
public class PaymentController {

    @PostMapping("payment")
    public String getPaymentLink(@RequestBody int amount) {

//        {
//            "api_username": "92ddcfab96e34a5f",
//                "account_name": "EUR3D1",
//                "amount": 10.00,
//                "order_reference": "asd125",
//                "nonce": "a9b7f7e79aaasdsde01b9902",
//                "timestamp": "2022-04-13T09:44:15+03:00",
//                "customer_url": "https://www.postimees.ee"
//        }

        // KOGU SISU LÄHEB SERVICE CLASSI SISSE
        EveryPayData everyPayData = new EveryPayData();
        everyPayData.setApi_username("92ddcfab96e34a5f");
        everyPayData.setAmount(99.00);
        everyPayData.setOrder_reference("abs" + Math.random());
        everyPayData.setNonce("ad" + Math.random() + new Date());
        everyPayData.setTimestamp(new Date().toString());
        System.out.println(new Date().toString()); // log4j2
        everyPayData.setCustomer_url("https://www.delfi.ee"); // serverisse üles heroku --
        // java ja front-end (Angular/React)

        // urli tõstame application.properties sisse
        String url = "https://igw-demo.every-pay.com/api/v4/payments/oneoff";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic OTJkZGNmYWI5NmUzNGE1Zjo4Y2QxOWU5OWU5YzJjMjA4ZWU1NjNhYmY3ZDBlNGRhZA==");

        HttpEntity<EveryPayData> httpEntity = new HttpEntity<>(everyPayData, headers);

        // üks ja sama new koguaeg --> @Autowired
        RestTemplate restTemplate = new RestTemplate(); // võimaldab teha HTTP päringuid
//        restTemplate.exchange(url, HttpMethod.POST,httpEntity,EveryPayResponse.class);

        return "";
    }
}
