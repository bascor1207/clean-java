package com.bastien_corre.cleanjava.wine_enthusiast;

import com.bastien_corre.cleanjava.wine_enthusiast.application.usecases.UpdateWineEnthusiastInfosCommand;
import com.bastien_corre.cleanjava.wine_enthusiast.application.usecases.UpdateWineEnthusiastInfosCommandHandler;
import com.bastien_corre.cleanjava.wine_enthusiast.application.usecases.UpdateWineEnthusiastWineRelevantInfosCommand;
import com.bastien_corre.cleanjava.wine_enthusiast.application.usecases.UpdateWineEnthusiastWineRelevantInfosCommandHandler;
import com.bastien_corre.cleanjava.wine_enthusiast.domain.model.WineEnthusiast;
import com.bastien_corre.cleanjava.wine_enthusiast.infra.adapters.in_memory.InMemoryWineEnthusiastRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ChangeWineEnthusiastAttributesTests {

    @Test
    void should_change_all_wine_enthusiast_infos() {
        var repository = createWineEnthusiastRepository();
        var existingWineEnthusiast = createWineEnthusiast();
        repository.save(existingWineEnthusiast);

        var command = new UpdateWineEnthusiastInfosCommand(existingWineEnthusiast.getId(), "Jack", "Daniels");
        var commandHandler = new UpdateWineEnthusiastInfosCommandHandler(repository);

        commandHandler.handle(command);

        var actualWineEnthusiast = repository.findById(existingWineEnthusiast.getId()).get();
        var expectedWineEnthusiast = new WineEnthusiast("123", "Jack", "Daniels", List.of("red", "white"));

        Assertions.assertEquals(expectedWineEnthusiast.getFirstName(), actualWineEnthusiast.getFirstName());
        Assertions.assertEquals(expectedWineEnthusiast.getLastName(), actualWineEnthusiast.getLastName());
    }

    @Test
    void should_not_change_wine_enthusiast_personal_infos_if_blank_or_null() {
        var repository = createWineEnthusiastRepository();
        var existingWineEnthusiast = createWineEnthusiast();
        repository.save(existingWineEnthusiast);
        var commandHandler = new UpdateWineEnthusiastInfosCommandHandler(repository);

        // First Command to test blank values
        var command = new UpdateWineEnthusiastInfosCommand(existingWineEnthusiast.getId(), "", "Daniels");
        commandHandler.handle(command);

        var actualWineEnthusiast = repository.findById(existingWineEnthusiast.getId()).get();
        var expectedWineEnthusiast = new WineEnthusiast("123", "John", "Daniels", List.of("red", "white"));

        Assertions.assertEquals(expectedWineEnthusiast.getFirstName(), actualWineEnthusiast.getFirstName());
        Assertions.assertEquals(expectedWineEnthusiast.getLastName(), actualWineEnthusiast.getLastName());


        // Second Command to test null values
        var secondCommand = new UpdateWineEnthusiastInfosCommand(existingWineEnthusiast.getId(), "Jack", null);
        commandHandler.handle(secondCommand);

        var secondExpectedWineEnthusiast = new WineEnthusiast("123", "Jack", "Daniels", List.of("red", "white"));

        Assertions.assertEquals(secondExpectedWineEnthusiast.getFirstName(), actualWineEnthusiast.getFirstName());
        Assertions.assertEquals(secondExpectedWineEnthusiast.getLastName(), actualWineEnthusiast.getLastName());

    }

    @Test
    void should_change_wine_enthusiast_preferred_wines() {
        var repository = createWineEnthusiastRepository();
        var existingWineEnthusiast = createWineEnthusiast();
        repository.save(existingWineEnthusiast);
        var commandHandler = new UpdateWineEnthusiastWineRelevantInfosCommandHandler(repository);

        var command = new UpdateWineEnthusiastWineRelevantInfosCommand(existingWineEnthusiast.getId(), List.of("white", "pink"));
        commandHandler.handle(command);

        var actualWineEnthusiast = repository.findById(existingWineEnthusiast.getId()).get();
        var expectedWineEnthusiast = new WineEnthusiast("123", "John", "Doe", List.of("white", "pink"));

        Assertions.assertEquals(expectedWineEnthusiast.getPreferredWines(), actualWineEnthusiast.getPreferredWines());
    }

    @Test
    void should_not_change_wine_enthusiast_preferred_wines_if_list_is_null_or_empty() {
        var repository = createWineEnthusiastRepository();
        var existingWineEnthusiast = createWineEnthusiast();
        repository.save(existingWineEnthusiast);
        var commandHandler = new UpdateWineEnthusiastWineRelevantInfosCommandHandler(repository);

        // First Command to test empty list
        var command = new UpdateWineEnthusiastWineRelevantInfosCommand(existingWineEnthusiast.getId(), List.of());
        commandHandler.handle(command);

        var actualWineEnthusiast = repository.findById(existingWineEnthusiast.getId()).get();
        var expectedWineEnthusiast = new WineEnthusiast("123", "John", "Doe", List.of("red", "white"));

        Assertions.assertEquals(expectedWineEnthusiast.getPreferredWines(), actualWineEnthusiast.getPreferredWines());

        // Second Command to test null list
        var secondCommand = new UpdateWineEnthusiastWineRelevantInfosCommand(existingWineEnthusiast.getId(), null);
        commandHandler.handle(secondCommand);

        var secondActualWineEnthusiast = repository.findById(existingWineEnthusiast.getId()).get();
        var secondExpectedWineEnthusiast = new WineEnthusiast("123", "John", "Doe", List.of("red", "white"));

        Assertions.assertEquals(secondExpectedWineEnthusiast.getPreferredWines(), secondActualWineEnthusiast.getPreferredWines());
    }

    private WineEnthusiast createWineEnthusiast() {
        return new WineEnthusiast("123", "John", "Doe", List.of("red", "white"));
    }

    private InMemoryWineEnthusiastRepository createWineEnthusiastRepository() {
        return new InMemoryWineEnthusiastRepository();
    }
}
