package com.bastien_corre.cleanjava.auth.infra.spring;

public class RegisterDTO {
    private String emailAddress;
    private String password;

    public RegisterDTO() {}

    public RegisterDTO(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }
}
