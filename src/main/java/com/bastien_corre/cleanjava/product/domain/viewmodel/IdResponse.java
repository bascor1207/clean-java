package com.bastien_corre.cleanjava.product.domain.viewmodel;

public class IdResponse {
    private String id;
    public IdResponse() {}
    public IdResponse(String id) {
        this.id = id;
    }

   public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
