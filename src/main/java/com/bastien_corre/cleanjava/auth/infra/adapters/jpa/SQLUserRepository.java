package com.bastien_corre.cleanjava.auth.infra.adapters.jpa;

import com.bastien_corre.cleanjava.auth.application.ports.UserRepository;
import com.bastien_corre.cleanjava.auth.domain.model.User;
import com.bastien_corre.cleanjava.core.infra.adapters.jpa.SQLBaseRepository;
import jakarta.persistence.EntityManager;

public class SQLUserRepository extends SQLBaseRepository<User> implements UserRepository {
    public SQLUserRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }
}
