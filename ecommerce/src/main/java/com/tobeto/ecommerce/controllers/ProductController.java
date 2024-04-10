package com.tobeto.ecommerce.controllers;
import com.tobeto.ecommerce.services.abstracts.ProductService;
import com.tobeto.ecommerce.services.dtos.requests.Product.ProductAddRequest;
import com.tobeto.ecommerce.services.dtos.requests.Product.ProductUpdateRequest;
import com.tobeto.ecommerce.services.dtos.responses.Product.ProductGetResponse;
import com.tobeto.ecommerce.services.dtos.responses.Product.ProductListingResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductGetResponse add(@RequestBody @Valid ProductAddRequest newProduct) {

        return  productService.add(newProduct);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ProductGetResponse update(@RequestBody ProductUpdateRequest product){
        return productService.update(product);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") int id){
        productService.delete(id);
    }
    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductListingResponse> getAll(){
       return productService.getALl();
    }

    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductGetResponse getById(@PathVariable("id") int id){

        return productService.getById(id);
    }

}
