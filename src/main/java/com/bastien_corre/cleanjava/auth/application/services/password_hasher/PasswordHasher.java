package com.bastien_corre.cleanjava.auth.application.services.password_hasher;

public interface PasswordHasher {
    String hash(String password);
    Boolean matches(String password, String hash);
}
