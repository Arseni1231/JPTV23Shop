package org.example.shop.helpers;

import org.example.shop.models.Customer;
import org.example.shop.models.Product;
import org.example.shop.models.Purchase;
import org.springframework.stereotype.Component;

@Component
public class PurchaseHelperImplements implements PurchaseHelper {

    @Override
    public void validatePurchase(Product product, Customer customer) {
        if (product == null || customer == null) {
            throw new IllegalArgumentException("Product and customer cannot be null");
        }

        if (product.getQuantity() <= 0) {
            throw new IllegalStateException("Product is out of stock");
        }

        if (customer.getBalance() < product.getPrice()) {
            throw new IllegalStateException("Insufficient customer balance");
        }
    }

    @Override
    public boolean printPurchaseReceipt(Purchase purchase) {
        if (purchase == null) {
            return false;
        }

        System.out.println("\n=== Квитанция о покупке ===");
        System.out.printf("Продукт: %s\n", purchase.getProduct().getName());
        System.out.printf("Цена: %.2f\n", purchase.getProduct().getPrice());
        System.out.printf("Пользователь: %s %s\n",
                purchase.getCustomer().getFirstname(),
                purchase.getCustomer().getLastname());
        System.out.printf("Дата покупки: %s\n", purchase.getPurchaseDate());
        System.out.println("=======================");

        return true;
    }
}
