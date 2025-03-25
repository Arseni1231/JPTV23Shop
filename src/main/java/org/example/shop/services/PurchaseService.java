package org.example.shop.services;

import org.example.shop.models.Purchase;
import java.time.LocalDateTime;

public interface PurchaseService {
    Purchase buyProduct(Long productId, Long customerId);
    double getIncome();
    double getIncome(LocalDateTime start, LocalDateTime end);
}
