package com.bastien_corre.cleanjava.core.infra.adapters;

import com.bastien_corre.cleanjava.core.domain.model.BaseEntity;

import java.util.Optional;

public interface BaseRepository<T extends BaseEntity> {
    public Optional<T> findById(String id);
    public void save(T entity);
    public void delete(T entity);
}
