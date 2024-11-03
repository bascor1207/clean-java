package com.bastien_corre.cleanjava.wine_enthusiast.infra.spring;

import an.awesome.pipelinr.Pipeline;
import com.bastien_corre.cleanjava.product.domain.viewmodel.IdResponse;
import com.bastien_corre.cleanjava.wine_enthusiast.application.usecases.*;
import com.bastien_corre.cleanjava.wine_enthusiast.domain.viewmodel.WineEnthusiastViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wineEnthusiasts")
@Transactional
public class WineEnthusiastController {

    private final Pipeline pipeline;

    public WineEnthusiastController(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    @PostMapping
    public ResponseEntity<IdResponse> createWineEnthusiast(@RequestBody CreateWineEnthusiastDTO createWineEnthusiastDTO) {
        var result = this.pipeline.send(new CreateWineEnthusiastCommand(createWineEnthusiastDTO.getFirstName(), createWineEnthusiastDTO.getLastName(), createWineEnthusiastDTO.getPreferredWines()));

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteWineEnthusiast(@PathVariable String id) {
        this.pipeline.send(new DeleteWineEnthusiastCommand(id));

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/{id}/personalInfos")
    public ResponseEntity<IdResponse> changeWineEnthusiastInfos(@PathVariable String id, @RequestBody WineEnthusiastInfosUpdateDTO wineEnthusiastInfosUpdateDTO) {
        this.pipeline.send(new UpdateWineEnthusiastInfosCommand(id, wineEnthusiastInfosUpdateDTO.getFirstName(), wineEnthusiastInfosUpdateDTO.getLastName()));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}/wineRelevantInfos")
    public ResponseEntity<IdResponse> changeWineEnthusiastPreferredWines(@PathVariable String id, @RequestBody WineEnthusiastPreferredWinesUpdateDTO wineEnthusiastPreferredWinesUpdateDTO) {
        this.pipeline.send(new UpdateWineEnthusiastWineRelevantInfosCommand(id, wineEnthusiastPreferredWinesUpdateDTO.getPreferredWines()));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<WineEnthusiastViewModel> getWineEnthusiastById(@PathVariable String id) {
        var wineEnthusiast = this.pipeline.send(new GetWineEnthusiastByIdCommand(id));

        return new ResponseEntity<>(new WineEnthusiastViewModel(wineEnthusiast.getId(), wineEnthusiast.getFirstName(), wineEnthusiast.getLastName(), wineEnthusiast.getPreferredWines()), HttpStatus.OK);
    }
}
