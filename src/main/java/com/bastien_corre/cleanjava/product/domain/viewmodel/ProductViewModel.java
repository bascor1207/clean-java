package com.bastien_corre.cleanjava.product.domain.viewmodel;

public class ProductViewModel {
    private String id;
    private String name;
    private String description;
    private Float price;

    public ProductViewModel() {}

    public ProductViewModel(String id, String name, String description, Float price) {
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

    public Float getPrice() {
        return price;
    }
}
