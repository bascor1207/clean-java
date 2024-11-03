package com.bastien_corre.cleanjava.wine_enthusiast.infra.adapters.jpa;

import com.bastien_corre.cleanjava.wine_enthusiast.domain.model.WineEnthusiast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SQLWineEnthusiastDataAccessor extends JpaRepository<WineEnthusiast, String> {}
