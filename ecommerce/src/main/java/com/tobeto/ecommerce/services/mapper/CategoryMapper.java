package com.tobeto.ecommerce.services.mapper;

import com.tobeto.ecommerce.entities.Category;
import com.tobeto.ecommerce.services.dtos.requests.category.AddCategoryRequest;
import com.tobeto.ecommerce.services.dtos.requests.category.UpdateCategoryRequest;
import com.tobeto.ecommerce.services.dtos.responses.category.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category categoryFromAddRequest(AddCategoryRequest request);

    AddCategoryResponse toCategoryAddResponse(Category category);

    Category categoryFromUpdateRequest(UpdateCategoryRequest request);

    UpdateCategoryResponse updateToCategoryDto(Category category);

    List<ListCategoryResponse> listToCategoryDto(List<Category> category);

    GetByIdCategoryResponse GetByIdToCategoryDto(Category category);

    DeleteCategoryResponse DeleteToCategoryDto(Category category);
}
