package com.bastien_corre.cleanjava.product.infra.spring;

public class CreateProductDTO {
    private String productName;
    private String productDescription;
    private Float productPrice;

    public CreateProductDTO() {}

    public CreateProductDTO(String productName, String productDescription, Float productPrice) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }
}
