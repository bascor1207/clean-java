package com.bastien_corre.cleanjava.wine_enthusiast;

import com.bastien_corre.cleanjava.core.domain.exceptions.NotFoundException;
import com.bastien_corre.cleanjava.wine_enthusiast.application.usecases.DeleteWineEnthusiastCommand;
import com.bastien_corre.cleanjava.wine_enthusiast.application.usecases.DeleteWineEnthusiastCommandHandler;
import com.bastien_corre.cleanjava.wine_enthusiast.domain.model.WineEnthusiast;
import com.bastien_corre.cleanjava.wine_enthusiast.infra.adapters.in_memory.InMemoryWineEnthusiastRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DeleteWineEnthusiastTests {

    private final InMemoryWineEnthusiastRepository wineEnthusiastRepository = new InMemoryWineEnthusiastRepository();

    private DeleteWineEnthusiastCommandHandler createCommandHandler() {
        return new DeleteWineEnthusiastCommandHandler(wineEnthusiastRepository);
    }

    @Test
    public void should_delete_the_wine_enthusiast() {
        var wineEnthusiast = new WineEnthusiast("123", "John", "Doe", List.of("red", "white"));
        wineEnthusiastRepository.save(wineEnthusiast);

        var command = new DeleteWineEnthusiastCommand(wineEnthusiast.getId());
        var commandHandler = createCommandHandler();

        commandHandler.handle(command);

        var wineEnthusiastQuery = wineEnthusiastRepository.findById(wineEnthusiast.getId());
        Assertions.assertTrue(wineEnthusiastQuery.isEmpty());
    }

    @Test
    public void should_throw_not_found_when_wine_enthusiast_does_not_exist() {
        var command = new DeleteWineEnthusiastCommand("nonExistentId");
        var commandHandler = createCommandHandler();

        var exception = Assertions.assertThrows(NotFoundException.class, () -> commandHandler.handle(command));

        Assertions.assertEquals("WineEnthusiast with key nonExistentId was not found", exception.getMessage());
    }
}
