package org.example.shop.services;

import org.example.shop.models.Product;
import org.example.shop.helpers.ProductHelper;
import org.example.shop.repositories.ProductRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImplements implements ProductService {
    private final ProductHelper productHelper;
    private final ProductRepository productRepository;

    public ProductServiceImplements(ProductHelper productHelper, ProductRepository productRepository) {
        this.productHelper = productHelper;
        this.productRepository = productRepository;
    }

    @Override
    public boolean add() {
        Optional<Product> optionalProduct = productHelper.create();
        if (optionalProduct.isEmpty()) {
            return false;
        }
        productRepository.save(optionalProduct.get());
        return true;
    }

    @Override
    public boolean edit() {
        Optional<Product> optionalModifyProduct = this.getProductForModify();
        if (optionalModifyProduct.isEmpty()) {
            return false;
        }
        Optional<Product> optionalModifiedProduct = productHelper.update(optionalModifyProduct.get());
        if (optionalModifiedProduct.isPresent()) {
            productRepository.save(optionalModifiedProduct.get());
            return true;
        }
        return false;
    }


    @Override
    public boolean print() {
        return productHelper.printList(productRepository.findAll(), true);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    // Вспомогательный метод (оставляем)
    private Optional<Product> getProductForModify() {
        Optional<Long> optionalProductId = productHelper.getIdModifyProduct(productRepository.findAll(), true);
        return optionalProductId.flatMap(productRepository::findById);
    }
}

