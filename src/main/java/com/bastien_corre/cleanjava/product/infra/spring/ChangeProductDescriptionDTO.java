package com.bastien_corre.cleanjava.product.infra.spring;

public class ChangeProductDescriptionDTO {

    private String description;

    public ChangeProductDescriptionDTO() {}

    public ChangeProductDescriptionDTO(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
