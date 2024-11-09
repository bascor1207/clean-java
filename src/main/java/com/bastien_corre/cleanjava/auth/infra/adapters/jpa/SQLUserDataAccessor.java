package com.bastien_corre.cleanjava.auth.infra.adapters.jpa;

import com.bastien_corre.cleanjava.auth.domain.model.User;
import org.springframework.data.repository.CrudRepository;

public interface SQLUserDataAccessor extends CrudRepository<User, String> {
    boolean existsByEmailAddress(String emailAddress);
}
