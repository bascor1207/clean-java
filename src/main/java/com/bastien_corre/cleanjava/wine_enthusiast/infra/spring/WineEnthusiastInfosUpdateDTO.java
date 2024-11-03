package com.bastien_corre.cleanjava.wine_enthusiast.infra.spring;

public class WineEnthusiastInfosUpdateDTO {
    private String firstName;
    private String lastName;

    public WineEnthusiastInfosUpdateDTO() {}

    public WineEnthusiastInfosUpdateDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
