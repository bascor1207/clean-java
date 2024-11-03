package com.bastien_corre.cleanjava.wine_enthusiast;

import com.bastien_corre.cleanjava.wine_enthusiast.application.usecases.CreateWineEnthusiastCommand;
import com.bastien_corre.cleanjava.wine_enthusiast.application.usecases.CreateWineEnthusiastCommandHandler;
import com.bastien_corre.cleanjava.wine_enthusiast.domain.model.WineEnthusiast;
import com.bastien_corre.cleanjava.wine_enthusiast.infra.adapters.in_memory.InMemoryWineEnthusiastRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CreateWineEnthusiastTests {
    @Test
    void should_create_wine_enthusiast() {
        var repository = new InMemoryWineEnthusiastRepository();
        var commandHandler = new CreateWineEnthusiastCommandHandler(repository);

        var command = new CreateWineEnthusiastCommand("John", "Doe", List.of("red", "white"));

        var idResponse = commandHandler.handle(command);

        var expectedWineEnthusiast = repository.findById(idResponse.getId()).get();
        var actualWineEnthusiast = new WineEnthusiast("123", "John", "Doe", List.of("red", "white"));

        Assertions.assertEquals(expectedWineEnthusiast.getFirstName(), actualWineEnthusiast.getFirstName());
    }
}
