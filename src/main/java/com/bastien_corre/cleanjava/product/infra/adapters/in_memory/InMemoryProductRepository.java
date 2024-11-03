package com.bastien_corre.cleanjava.product.infra.adapters.in_memory;

import com.bastien_corre.cleanjava.core.infra.adapters.in_memory.InMemoryBaseRepository;
import com.bastien_corre.cleanjava.product.application.ports.ProductRepository;
import com.bastien_corre.cleanjava.product.domain.model.Product;

public class InMemoryProductRepository extends InMemoryBaseRepository<Product> implements ProductRepository {}
