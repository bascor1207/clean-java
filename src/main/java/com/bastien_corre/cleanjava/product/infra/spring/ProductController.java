package com.bastien_corre.cleanjava.product.infra.spring;

import an.awesome.pipelinr.Pipeline;
import com.bastien_corre.cleanjava.product.application.usecases.ChangeProductDescriptionCommand;
import com.bastien_corre.cleanjava.product.application.usecases.CreateProductCommand;
import com.bastien_corre.cleanjava.product.application.usecases.DeleteProductCommand;
import com.bastien_corre.cleanjava.product.application.usecases.GetProductByIdCommand;
import com.bastien_corre.cleanjava.product.domain.viewmodel.IdResponse;
import com.bastien_corre.cleanjava.product.domain.viewmodel.ProductViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@Transactional
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

    @PatchMapping(value = "/{id}/description")
    public ResponseEntity<Void> changeProductDescription(@PathVariable String id, @RequestBody ChangeProductDescriptionDTO changeProductDescriptionDTO) {
        this.pipeline.send(new ChangeProductDescriptionCommand(id, changeProductDescriptionDTO.getDescription()));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        this.pipeline.send(new DeleteProductCommand(id));

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductViewModel> getProductById(@PathVariable String id) {
        var product = this.pipeline.send(new GetProductByIdCommand(id));

        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
