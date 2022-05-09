package ee.mihkel.webshop.service;

import ee.mihkel.webshop.model.database.Product;

import java.util.List;

public interface OrderService {

    double calculateOrderSum(List<Product> products);

    Long saveToDatabase(List<Product> products, double orderSum);

    List<Product> getAllProductsFromDb(List<Product> products);
}
