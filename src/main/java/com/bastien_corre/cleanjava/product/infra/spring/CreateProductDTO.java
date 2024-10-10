package com.bastien_corre.cleanjava.product.infra.spring;

public class CreateProductDTO {
    private String productName;
    private String productDescription;
    private Integer productPrice;

    public CreateProductDTO() {}

    public CreateProductDTO(String productName, String productDescription, Integer productPrice) {
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

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }
}
