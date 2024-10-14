package com.bastien_corre.cleanjava.product.domain.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
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

    public String getDescription() {
        return description;
    }

    public Integer getPrice() {
        return price;
    }

    public void changeDescription(String description) {
        this.description = description;
    }
}
