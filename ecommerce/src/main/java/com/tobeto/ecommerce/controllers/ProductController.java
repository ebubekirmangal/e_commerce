package com.tobeto.ecommerce.controllers;

import com.tobeto.ecommerce.services.abstracts.ProductService;
import com.tobeto.ecommerce.services.dtos.requests.Product.AddProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.Product.DeleteProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.Product.GetByIdProductRequest;
import com.tobeto.ecommerce.services.dtos.requests.Product.UpdateProductRequest;
import com.tobeto.ecommerce.services.dtos.responses.Product.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public AddProductResponse add(@RequestBody @Valid AddProductRequest request) {

        return  productService.add(request);
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
    @GetMapping("/getAllForCustomer")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllProductCustomerResponse> search(@RequestParam(required = false) String productName, @RequestParam(required = false) Double minPrice, @RequestParam(required = false) Double maxPrice, @RequestParam(required = false) String categoryName){
       return productService.search(productName, minPrice,maxPrice,categoryName);
    }
    @GetMapping("/getAllForAdmin")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllProductAdminResponse> search(@RequestParam(required = false) String productName, @RequestParam(required = false) String categoryName){
        return productService.search(productName,categoryName);
    }
    @GetMapping("/getLast5Product")
    @ResponseStatus(HttpStatus.OK)
    public List<LastAddProductResponse> getLastAdded(){
        return productService.getLastAdded();
    }
    @GetMapping("/getById")
    @ResponseStatus(HttpStatus.OK)
    public GetByIdProductResponse getById(GetByIdProductRequest request){
        return productService.getById(request);
    }



}
