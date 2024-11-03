package com.bastien_corre.cleanjava.product.infra.adapters.jpa;

import com.bastien_corre.cleanjava.core.infra.adapters.jpa.SQLBaseRepository;
import com.bastien_corre.cleanjava.product.application.ports.ProductRepository;
import com.bastien_corre.cleanjava.product.domain.model.Product;
import jakarta.persistence.EntityManager;

public class SQLProductRepository extends SQLBaseRepository<Product> implements ProductRepository {
    public SQLProductRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Product> getEntityClass() {
        return Product.class;
    }
}
