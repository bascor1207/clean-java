package com.bastien_corre.cleanjava.wine_enthusiast.infra.spring;

import com.bastien_corre.cleanjava.wine_enthusiast.application.ports.WineEnthusiastRepository;
import com.bastien_corre.cleanjava.wine_enthusiast.infra.adapters.jpa.SQLWineEnthusiastRepository;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WineEnthusiastConfiguration {

    @Bean
    public WineEnthusiastRepository wineEnthusiastRepository(EntityManager entityManager) {
        return new SQLWineEnthusiastRepository(entityManager);
    }

}
