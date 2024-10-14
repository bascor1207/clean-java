package com.bastien_corre.cleanjava.product.infra.adapters.in_memory;

import com.bastien_corre.cleanjava.product.application.ports.ProductRepository;
import com.bastien_corre.cleanjava.product.domain.model.Product;

import java.util.HashMap;
import java.util.Map;

public class InMemoryProductRepository implements ProductRepository {
    private Map<String, Product> products = new HashMap<>();

    @Override
    public Product findById(String id) {
        return products.get(id);
    }

    @Override
    public void save(Product product) {
        this.products.put(product.getId(), product);
    }
}
