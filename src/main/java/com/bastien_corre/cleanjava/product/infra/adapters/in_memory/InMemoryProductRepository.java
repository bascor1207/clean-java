package com.bastien_corre.cleanjava.product.infra.adapters.in_memory;

import com.bastien_corre.cleanjava.product.application.ports.ProductRepository;
import com.bastien_corre.cleanjava.product.domain.model.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryProductRepository implements ProductRepository {
    private Map<String, Product> products = new HashMap<>();

    @Override
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public void save(Product product) {
        this.products.put(product.getId(), product);
    }

    @Override
    public void delete(Product product) {
        this.products.remove(product.getId());
    }
}
