package com.bastien_corre.cleanjava.auth.domain.model;

import com.bastien_corre.cleanjava.core.domain.model.BaseEntity;

public class User extends BaseEntity {
    private String emailAddress;
    private String password;

    public User() {}

    public User(String id, String emailAddress, String password) {
        this.id = id;
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
