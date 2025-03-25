package org.example.shop.services;

import org.example.shop.models.Product;



public interface ProductService extends AppService<Product> {
    Product getProductById(Long id);
    void saveProduct(Product product);
}
