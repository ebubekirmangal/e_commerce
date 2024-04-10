package com.tobeto.ecommerce.services.abstracts;

import com.tobeto.ecommerce.services.dtos.requests.category.AddCategoryRequest;
import com.tobeto.ecommerce.services.dtos.requests.category.DeleteCategoryRequest;
import com.tobeto.ecommerce.services.dtos.requests.category.GetByIdCategoryRequest;
import com.tobeto.ecommerce.services.dtos.requests.category.UpdateCategoryRequest;
import com.tobeto.ecommerce.services.dtos.responses.category.*;

import java.util.List;

public interface CategoryService {

    AddCategoryResponse add(AddCategoryRequest request);

    UpdateCategoryResponse update(UpdateCategoryRequest request);

    List<ListCategoryResponse> getAll();

    GetByIdCategoryResponse getById(GetByIdCategoryRequest request);

    DeleteCategoryResponse delete(DeleteCategoryRequest request);
}
