package com.bastien_corre.cleanjava.auth;

import com.bastien_corre.cleanjava.auth.application.services.password_hasher.BcryptPasswordHasher;
import com.bastien_corre.cleanjava.auth.application.services.password_hasher.PasswordHasher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordHasherTests {
    public PasswordHasher createPasswordHasher() {
        return new BcryptPasswordHasher();
    }

    @Test
    void should_hash_password() {
        var clearPassword = "password";
        var passwordHasher = createPasswordHasher();

        var hashedPassword = passwordHasher.hash(clearPassword);

        Assertions.assertTrue(passwordHasher.matches(clearPassword, hashedPassword));
    }
}
