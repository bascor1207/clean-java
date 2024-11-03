package com.bastien_corre.cleanjava.wine_enthusiast.infra.spring;

import com.bastien_corre.cleanjava.wine_enthusiast.application.ports.WineEnthusiastRepository;
import com.bastien_corre.cleanjava.wine_enthusiast.application.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WineEnthusiastUseCaseConfiguration {

    @Bean
    public CreateWineEnthusiastCommandHandler CreateWineEnthusiastCommandHandler(WineEnthusiastRepository wineEnthusiastRepository) {
        return new CreateWineEnthusiastCommandHandler(wineEnthusiastRepository);
    }

    @Bean
    public DeleteWineEnthusiastCommandHandler DeleteWineEnthusiastCommandHandler(WineEnthusiastRepository wineEnthusiastRepository) {
        return new DeleteWineEnthusiastCommandHandler(wineEnthusiastRepository);
    }

    @Bean
    public UpdateWineEnthusiastInfosCommandHandler UpdateWineEnthusiastInfosCommandHandler(WineEnthusiastRepository wineEnthusiastRepository) {
        return new UpdateWineEnthusiastInfosCommandHandler(wineEnthusiastRepository);
    }

    @Bean
    public UpdateWineEnthusiastWineRelevantInfosCommandHandler UpdateWineEnthusiastWineRelevantInfosCommandHandler(WineEnthusiastRepository wineEnthusiastRepository) {
        return new UpdateWineEnthusiastWineRelevantInfosCommandHandler(wineEnthusiastRepository);
    }

    @Bean
    public GetWineEnthusiastByIdCommandHandler GetWineEnthusiastByIdCommandHandler(WineEnthusiastRepository wineEnthusiastRepository) {
        return new GetWineEnthusiastByIdCommandHandler(wineEnthusiastRepository);
    }
}
