package com.bastien_corre.cleanjava.auth.infra.adapters.jpa;

import com.bastien_corre.cleanjava.auth.application.ports.UserRepository;
import com.bastien_corre.cleanjava.auth.domain.model.User;
import com.bastien_corre.cleanjava.core.infra.adapters.jpa.SQLBaseRepository;
import jakarta.persistence.EntityManager;

public class SQLUserRepository extends SQLBaseRepository<User> implements UserRepository {
    private final SQLUserDataAccessor userDataAccessor;

    public SQLUserRepository(EntityManager entityManager, SQLUserDataAccessor userDataAccessor) {
        super(entityManager);
        this.userDataAccessor = userDataAccessor;
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public boolean isEmailAvailable(String emailAddress) {
        return !userDataAccessor.existsByEmailAddress(emailAddress);
    }
}
