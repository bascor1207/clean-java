package com.bastien_corre.cleanjava.wine_enthusiast.application.usecases;

import an.awesome.pipelinr.Command;
import com.bastien_corre.cleanjava.core.domain.exceptions.NotFoundException;
import com.bastien_corre.cleanjava.wine_enthusiast.application.ports.WineEnthusiastRepository;

public class UpdateWineEnthusiastWineRelevantInfosCommandHandler implements Command.Handler<UpdateWineEnthusiastWineRelevantInfosCommand, Void> {
    private final WineEnthusiastRepository wineEnthusiastRepository;

    public UpdateWineEnthusiastWineRelevantInfosCommandHandler(WineEnthusiastRepository wineEnthusiastRepository) {
        this.wineEnthusiastRepository = wineEnthusiastRepository;
    }

    @Override
    public Void handle (UpdateWineEnthusiastWineRelevantInfosCommand updateWineEnthusiastWineRelevantInfosCommand) {
        var wineEnthusiast = this.wineEnthusiastRepository.findById(updateWineEnthusiastWineRelevantInfosCommand.id()).orElseThrow(
                () -> new NotFoundException(
                        "WineEnthusiast",
                        updateWineEnthusiastWineRelevantInfosCommand.id()
        ));

        wineEnthusiast.changeWineRelevantInfos(updateWineEnthusiastWineRelevantInfosCommand);
        this.wineEnthusiastRepository.save(wineEnthusiast);

        return null;
    }
}
