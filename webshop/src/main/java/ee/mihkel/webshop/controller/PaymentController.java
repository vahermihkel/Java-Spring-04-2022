package ee.mihkel.webshop.controller;

import ee.mihkel.webshop.model.database.Product;
import ee.mihkel.webshop.model.request.input.EveryPayResponse;
import ee.mihkel.webshop.model.request.output.EveryPayData;
import ee.mihkel.webshop.service.OrderService;
import ee.mihkel.webshop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;
    // nagu PaymentService paymentService = new PaymentService()
    // aga üks mälukoht koguaeg
    // selleks et @Autowired saaks panna peab olema @Component või laadset

    @Autowired
    OrderService orderService;

    @PostMapping("payment")  // localhost:8080/payment    Body    80   text
    public ResponseEntity<String> getPaymentLink(@RequestBody List<Product> products) {
        // Tooted --- nimedega+hindadega
        // Maksma --- Tellimuse nr-t
        // Salvestan andmebaasi -> maksmata kujul
        // Võtan andmebaasist tema ID (mis on genereeritud)
        // ---> Lähen maksma
        List<Product> originalProducts = orderService.getAllProductsFromDb(products);
        double orderSum = orderService.calculateOrderSum(originalProducts);
        Long id = orderService.saveToDatabase(originalProducts, orderSum);
        return ResponseEntity.ok().body(paymentService.getPaymentLink(orderSum, id));
    }

//    @PostMapping("check-payment")
//    public boolean checkIfPaid() {
//        // Kui on makstud, muudan andmebaasis makstuks
//        return true;
//    }
}
