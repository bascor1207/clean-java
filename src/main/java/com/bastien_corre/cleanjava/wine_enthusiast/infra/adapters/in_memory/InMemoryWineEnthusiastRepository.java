package com.bastien_corre.cleanjava.wine_enthusiast.infra.adapters.in_memory;

import com.bastien_corre.cleanjava.core.infra.adapters.in_memory.InMemoryBaseRepository;
import com.bastien_corre.cleanjava.wine_enthusiast.application.ports.WineEnthusiastRepository;
import com.bastien_corre.cleanjava.wine_enthusiast.domain.model.WineEnthusiast;

public class InMemoryWineEnthusiastRepository extends InMemoryBaseRepository<WineEnthusiast> implements WineEnthusiastRepository {}
