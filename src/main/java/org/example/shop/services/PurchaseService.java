package org.example.shop.services;

import org.example.shop.models.Customer;
import org.example.shop.models.Product;
import org.example.shop.models.Purchase;
import org.example.shop.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    public Purchase buyProduct(Long productId, Long customerId) {
        Product product = productService.getProductById(productId);
        Customer customer = customerService.getCustomerById(customerId);

        if (product.getQuantity() <= 0) {
            throw new RuntimeException("Product out of stock");
        }

        if (customer.getBalance() < product.getPrice()) {
            throw new RuntimeException("Insufficient funds");
        }

        product.setQuantity(product.getQuantity() - 1);
        customer.setBalance(customer.getBalance() - product.getPrice());

        productService.saveProduct(product);
        customerService.saveCustomer(customer);

        Purchase purchase = new Purchase();
        purchase.setProduct(product);
        purchase.setCustomer(customer);
        purchase.setPurchaseDate(LocalDateTime.now());

        return purchaseRepository.save(purchase);
    }

    public double getIncome(LocalDateTime start, LocalDateTime end) {
        List<Purchase> purchases = purchaseRepository.findByPurchaseDateBetween(start, end);
        return purchases.stream().mapToDouble(p -> p.getProduct().getPrice()).sum();
    }
}


