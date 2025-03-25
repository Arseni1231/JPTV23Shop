package org.example.shop.helpers;

import org.example.shop.models.Customer;
import org.example.shop.models.Product;
import org.example.shop.models.Purchase;

public interface PurchaseHelper {
    void validatePurchase(Product product, Customer customer);
    boolean printPurchaseReceipt(Purchase purchase);
}
