package com.bastien_corre.cleanjava.product.domain.model;


import com.bastien_corre.cleanjava.core.domain.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Float price;

    public Product() {}

    public Product(String id, String name, String description, Float price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Float getPrice() {
        return price;
    }

    public void changeDescription(String description) {
        this.description = description;
    }
}
