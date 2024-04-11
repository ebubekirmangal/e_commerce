package com.tobeto.ecommerce.controllers;

import com.tobeto.ecommerce.services.abstracts.CategoryService;
import com.tobeto.ecommerce.services.dtos.requests.category.AddCategoryRequest;
import com.tobeto.ecommerce.services.dtos.requests.category.DeleteCategoryRequest;
import com.tobeto.ecommerce.services.dtos.requests.category.GetByIdCategoryRequest;
import com.tobeto.ecommerce.services.dtos.requests.category.UpdateCategoryRequest;
import com.tobeto.ecommerce.services.dtos.responses.category.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoriesController {

    private final CategoryService categoryService;

    @Autowired
    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public AddCategoryResponse add(AddCategoryRequest request){
        return categoryService.add(request);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public UpdateCategoryResponse update(UpdateCategoryRequest request){
        return categoryService.update(request);
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<ListCategoryResponse> getAll(){
        return categoryService.getAll();
    }
    @GetMapping("/getById")
    @ResponseStatus(HttpStatus.OK)
    public GetByIdCategoryResponse getById(GetByIdCategoryRequest request){
        return categoryService.getById(request);
    }
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public DeleteCategoryResponse delete(DeleteCategoryRequest request){
        return categoryService.delete(request);
    }
}
