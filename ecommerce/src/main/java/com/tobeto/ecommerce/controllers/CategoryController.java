package com.tobeto.ecommerce.controllers;

import com.tobeto.ecommerce.services.abstracts.CategoryService;
import com.tobeto.ecommerce.services.dtos.requests.Category.CategoryAddRequest;
import com.tobeto.ecommerce.services.dtos.responses.Category.CategoryAddResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryAddResponse add(CategoryAddRequest request){
        return categoryService.add(request);
    }

}
