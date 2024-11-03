package com.bastien_corre.cleanjava.wine_enthusiast.application.usecases;

import an.awesome.pipelinr.Command;
import com.bastien_corre.cleanjava.core.domain.exceptions.IllegalArgumentException;
import com.bastien_corre.cleanjava.core.utils.StringUtils;
import com.bastien_corre.cleanjava.product.domain.viewmodel.IdResponse;
import com.bastien_corre.cleanjava.wine_enthusiast.application.ports.WineEnthusiastRepository;
import com.bastien_corre.cleanjava.wine_enthusiast.domain.model.WineEnthusiast;

import java.util.UUID;

public class CreateWineEnthusiastCommandHandler implements Command.Handler<CreateWineEnthusiastCommand, IdResponse> {
    private final WineEnthusiastRepository wineEnthusiastRepository;

    public CreateWineEnthusiastCommandHandler(WineEnthusiastRepository wineEnthusiastRepository) {
        this.wineEnthusiastRepository = wineEnthusiastRepository;
    }

    @Override
    public IdResponse handle(CreateWineEnthusiastCommand command) {
        var wineEnthusiast = new WineEnthusiast(
                UUID.randomUUID().toString(),
                StringUtils.requireNonNullNorBlankElseThrow(command.firstName(),
                        () -> new IllegalArgumentException(
                                "CreateWineEnthusiastCommand",
                                "firstName",
                                StringUtils.isBlank(command.firstName()) ? "blank" : command.firstName(),
                                command.firstName().getClass().getSimpleName()
                        )
                ),
                StringUtils.requireNonNullNorBlankElseThrow(command.lastName(),
                        () -> new IllegalArgumentException(
                                "CreateWineEnthusiastCommand",
                                "lastName",
                                command.lastName().toString(),
                                command.firstName().getClass().getSimpleName()
                        )
                ),
                command.preferredWines()
        );
        wineEnthusiastRepository.save(wineEnthusiast);

        return new IdResponse(wineEnthusiast.getId());
    }
}
