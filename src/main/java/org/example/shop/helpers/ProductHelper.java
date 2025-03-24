package org.example.shop.helpers;

import org.example.shop.models.Product;
import java.util.List;
import java.util.Optional;

public interface ProductHelper extends AppHelper<Product> {
    Optional<Long> getIdModifyProduct(List<Product> products, boolean enabled);
}
