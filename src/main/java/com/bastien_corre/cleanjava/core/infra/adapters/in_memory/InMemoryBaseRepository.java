package com.bastien_corre.cleanjava.core.infra.adapters.in_memory;

import com.bastien_corre.cleanjava.core.domain.model.BaseEntity;
import com.bastien_corre.cleanjava.core.infra.adapters.BaseRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class InMemoryBaseRepository<T extends BaseEntity> implements BaseRepository<T> {
    protected Map<String, T> entities = new HashMap<>();

    @Override
    public Optional<T> findById(String id) {
        return Optional.ofNullable(this.entities.get(id));
    }

    @Override
    public void save(T entity) {
        this.entities.put(entity.getId(), entity);
    }

    @Override
    public void delete(T entity) {
        this.entities.remove(entity.getId());
    }

    @Override
    public void clear() {
        this.entities.clear();
    }
}
