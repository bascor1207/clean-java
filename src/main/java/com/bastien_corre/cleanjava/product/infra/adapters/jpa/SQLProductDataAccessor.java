package com.bastien_corre.cleanjava.product.infra.adapters.jpa;

import com.bastien_corre.cleanjava.product.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SQLProductDataAccessor extends JpaRepository<Product, String> {
}
