package com.bastien_corre.cleanjava.product.infra.adapters.jpa;

import com.bastien_corre.cleanjava.product.application.ports.ProductRepository;
import com.bastien_corre.cleanjava.product.domain.model.Product;

public class SQLProductRepository implements ProductRepository {
    private final SQLProductDataAccessor sqlProductDataAccessor;

    public SQLProductRepository(SQLProductDataAccessor sqlProductDataAccessor) {
        this.sqlProductDataAccessor = sqlProductDataAccessor;
    }

    @Override
    public Product findById(String id) {
       return this.sqlProductDataAccessor.findById(id).orElse(null);
    }

    @Override
    public void save(Product product) {
        this.sqlProductDataAccessor.save(product);
    }
}
