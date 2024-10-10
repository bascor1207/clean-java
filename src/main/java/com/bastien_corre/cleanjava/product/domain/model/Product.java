package com.bastien_corre.cleanjava.product.domain.model;

public class Product {
    private String id;
    private String name;
    private String description;
    private Integer price;

    public Product() {}

    public Product(String id, String name, String description, Integer price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
