package com.bastien_corre.cleanjava.product.infra.adapters.jpa;

import com.bastien_corre.cleanjava.product.application.ports.ProductRepository;
import com.bastien_corre.cleanjava.product.domain.model.Product;

import java.util.Optional;

public class SQLProductRepository implements ProductRepository {
    private final SQLProductDataAccessor sqlProductDataAccessor;

    public SQLProductRepository(SQLProductDataAccessor sqlProductDataAccessor) {
        this.sqlProductDataAccessor = sqlProductDataAccessor;
    }

    @Override
    public Optional<Product> findById(String id) {
       return this.sqlProductDataAccessor.findById(id);
    }

    @Override
    public void save(Product product) {
        this.sqlProductDataAccessor.save(product);
    }

    @Override
    public void delete(Product product) {
        this.sqlProductDataAccessor.delete(product);
    }
}
