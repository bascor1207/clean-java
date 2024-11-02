package com.bastien_corre.cleanjava.product.application.ports;

import com.bastien_corre.cleanjava.product.domain.model.Product;

import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(String id);

    void save(Product product);

    void delete(Product id);
}
