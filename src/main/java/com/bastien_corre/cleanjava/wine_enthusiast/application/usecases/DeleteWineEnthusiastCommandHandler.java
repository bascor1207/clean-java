package com.bastien_corre.cleanjava.wine_enthusiast.application.usecases;

import an.awesome.pipelinr.Command;
import com.bastien_corre.cleanjava.core.domain.exceptions.NotFoundException;
import com.bastien_corre.cleanjava.wine_enthusiast.application.ports.WineEnthusiastRepository;

public class DeleteWineEnthusiastCommandHandler implements Command.Handler<DeleteWineEnthusiastCommand, Void> {
    private final WineEnthusiastRepository wineEnthusiastRepository;
    public DeleteWineEnthusiastCommandHandler(WineEnthusiastRepository wineEnthusiastRepository) {
        this.wineEnthusiastRepository = wineEnthusiastRepository;
    }

    @Override
    public Void handle(DeleteWineEnthusiastCommand deleteWineEnthusiastCommand) {
        var wineEnthusiast = wineEnthusiastRepository.findById(deleteWineEnthusiastCommand.id()).orElseThrow(() -> new NotFoundException("WineEnthusiast", deleteWineEnthusiastCommand.id()));

        wineEnthusiastRepository.delete(wineEnthusiast);
        return null;
    }
}
