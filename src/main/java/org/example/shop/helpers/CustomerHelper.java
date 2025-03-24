package org.example.shop.helpers;

import org.example.shop.models.Customer;
import org.example.shop.models.Product;
import java.util.List;
import java.util.Optional;

// Интерфейс для работы с покупателями
public interface CustomerHelper extends AppHelper<Customer> {
    Optional<Long> getIdModifyCustomer(List<Customer> customers, boolean enabled);
    List<Long> listIdPurchasedProducts(List<Product> products, boolean enabled);
}
