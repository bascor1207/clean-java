package com.bastien_corre.cleanjava.wine_enthusiast.application.usecases;

import an.awesome.pipelinr.Command;
import com.bastien_corre.cleanjava.core.domain.exceptions.NotFoundException;
import com.bastien_corre.cleanjava.wine_enthusiast.application.ports.WineEnthusiastRepository;
import com.bastien_corre.cleanjava.wine_enthusiast.domain.viewmodel.WineEnthusiastViewModel;

public class GetWineEnthusiastByIdCommandHandler implements Command.Handler<GetWineEnthusiastByIdCommand, WineEnthusiastViewModel> {
    private final WineEnthusiastRepository wineEnthusiastRepository;

    public GetWineEnthusiastByIdCommandHandler(WineEnthusiastRepository wineEnthusiastRepository) {
        this.wineEnthusiastRepository = wineEnthusiastRepository;
    }

    @Override
    public WineEnthusiastViewModel handle(GetWineEnthusiastByIdCommand getWineEnthusiastByIdCommand) {
        var wineEnthusiast = this.wineEnthusiastRepository.findById(getWineEnthusiastByIdCommand.id()).orElseThrow(
                () -> new NotFoundException(
                        "WineEnthusiast",
                        getWineEnthusiastByIdCommand.id()
        ));

        return new WineEnthusiastViewModel(wineEnthusiast.getId(), wineEnthusiast.getFirstName(), wineEnthusiast.getLastName(), wineEnthusiast.getPreferredWines());
    }
}
