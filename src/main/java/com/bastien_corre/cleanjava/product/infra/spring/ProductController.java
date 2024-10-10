package com.bastien_corre.cleanjava.product.infra.spring;

import an.awesome.pipelinr.Pipeline;
import com.bastien_corre.cleanjava.product.application.usecases.CreateProductCommand;
import com.bastien_corre.cleanjava.product.application.usecases.CreateProductCommandHandler;
import com.bastien_corre.cleanjava.product.domain.viewmodel.IdResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final Pipeline pipeline;

    public ProductController(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    @PostMapping
    public ResponseEntity<IdResponse> createProduct(@RequestBody CreateProductDTO createProductDTO) {
       var result = this.pipeline.send(new CreateProductCommand(createProductDTO.getProductName(), createProductDTO.getProductDescription(), createProductDTO.getProductPrice()));

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
