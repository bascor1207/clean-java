package com.bastien_corre.cleanjava.auth.application.services.password_hasher;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptPasswordHasher implements PasswordHasher {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String hash(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public Boolean matches(String password, String hash) {
        return passwordEncoder.matches(password, hash);
    }
}
