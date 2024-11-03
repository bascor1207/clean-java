package com.bastien_corre.cleanjava.wine_enthusiast.infra.adapters.jpa;

import com.bastien_corre.cleanjava.core.infra.adapters.jpa.SQLBaseRepository;
import com.bastien_corre.cleanjava.wine_enthusiast.application.ports.WineEnthusiastRepository;
import com.bastien_corre.cleanjava.wine_enthusiast.domain.model.WineEnthusiast;
import jakarta.persistence.EntityManager;

public class SQLWineEnthusiastRepository extends SQLBaseRepository<WineEnthusiast> implements WineEnthusiastRepository {
    public SQLWineEnthusiastRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<WineEnthusiast> getEntityClass() {
        return WineEnthusiast.class;
    }
}
