package com.tobeto.ecommerce.services.abstracts;

import com.tobeto.ecommerce.services.dtos.requests.Category.AddCategoryRequest;
import com.tobeto.ecommerce.services.dtos.responses.Category.AddCategoryResponse;

public interface CategoryService {

    AddCategoryResponse add(AddCategoryRequest request);
}
