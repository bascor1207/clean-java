package com.bastien_corre.cleanjava.core.infra.adapters.jpa;

import com.bastien_corre.cleanjava.core.domain.model.BaseEntity;
import com.bastien_corre.cleanjava.core.infra.adapters.BaseRepository;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public abstract class SQLBaseRepository<T extends BaseEntity> implements BaseRepository<T> {
    private final EntityManager entityManager;

    public SQLBaseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Class<T> getEntityClass();

    @Override
    public Optional<T> findById(String id) {
        return Optional.ofNullable(this.entityManager.find(getEntityClass(), id));
    }

    @Override
    public void save(T entity) {
        this.entityManager.persist(entity);
    }

    @Override
    public void delete(T entity) {
        this.entityManager.remove(entity);
    }

    @Override
    public void clear() {
        this.entityManager.createQuery("DELETE FROM " + getEntityClass().getSimpleName()).executeUpdate();
    }
}
