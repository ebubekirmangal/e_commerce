package com.tobeto.ecommerce.controllers;

import com.tobeto.ecommerce.services.abstracts.ProductService;
import com.tobeto.ecommerce.services.dtos.requests.product.AddProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.product.DeleteProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.product.GetByIdProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.product.UpdateProductRequest;
import com.tobeto.ecommerce.services.dtos.responses.product.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductsController {
    private ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public AddProductResponse add(@RequestBody @Valid AddProductRequest newProduct) {

        return  productService.add(newProduct);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public UpdateProductResponse update(@RequestBody UpdateProductRequest product){
        return productService.update(product);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public DeleteProductResponse delete(DeleteProductRequest request){
        return productService.delete(request);
    }
    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<ListProductResponse> getAll(){
       return productService.getALl();
    }

    @GetMapping("/getById")
    @ResponseStatus(HttpStatus.OK)
    public GetByIdProductResponse getById(GetByIdProductRequest request){

        return productService.getById(request);
    }

}
