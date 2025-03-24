package org.example.shop.helpers;

import org.example.shop.models.Customer;
import org.example.shop.models.Product;
import org.springframework.stereotype.Component;

@Component
public class PurchaseHelper {
    public void validatePurchase(Product product, Customer customer) {
        if (product.getQuantity() <= 0) {
            throw new RuntimeException("Продукт вышел с продажи");
        }
        if (customer.getBalance() < product.getPrice()) {
            throw new RuntimeException("Недостаточно средств");
        }
    }
}
