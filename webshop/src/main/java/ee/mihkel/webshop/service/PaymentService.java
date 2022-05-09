package ee.mihkel.webshop.service;

public interface PaymentService {

    String getPaymentLink(double amount, Long orderId);

    Boolean checkIfOrderPaid(Long orderId, String paymentRef);
}

