package com.bastien_corre.cleanjava.product.application.ports;

import com.bastien_corre.cleanjava.product.domain.model.Product;

public interface ProductRepositoryInterface {
    Product findById(String id);

    void save(Product product);
}