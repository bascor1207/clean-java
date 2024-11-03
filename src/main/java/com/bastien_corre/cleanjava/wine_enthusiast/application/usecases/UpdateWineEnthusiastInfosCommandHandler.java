package com.bastien_corre.cleanjava.wine_enthusiast.application.usecases;

import an.awesome.pipelinr.Command;
import com.bastien_corre.cleanjava.core.domain.exceptions.NotFoundException;
import com.bastien_corre.cleanjava.wine_enthusiast.application.ports.WineEnthusiastRepository;

public class UpdateWineEnthusiastInfosCommandHandler implements Command.Handler<UpdateWineEnthusiastInfosCommand, Void> {
    private final WineEnthusiastRepository wineEnthusiastRepository;

    public UpdateWineEnthusiastInfosCommandHandler(WineEnthusiastRepository wineEnthusiastRepository) {
        this.wineEnthusiastRepository = wineEnthusiastRepository;
    }

    @Override
    public Void handle(UpdateWineEnthusiastInfosCommand updateWineEnthusiastInfosCommand) {
        var wineEnthusiast = this.wineEnthusiastRepository.findById(updateWineEnthusiastInfosCommand.id()).orElseThrow(
                () -> new NotFoundException(
                        "WineEnthusiast",
                        updateWineEnthusiastInfosCommand.id()
        ));

        wineEnthusiast.changePersonalInfos(updateWineEnthusiastInfosCommand);
        this.wineEnthusiastRepository.save(wineEnthusiast);

        return null;
    }
}
