package com.tobeto.ecommerce.services.mapper;

import com.tobeto.ecommerce.entities.Category;
import com.tobeto.ecommerce.services.dtos.requests.Category.AddCategoryRequest;
import com.tobeto.ecommerce.services.dtos.responses.Category.AddCategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category categoryFromAddRequest(AddCategoryRequest request);

    AddCategoryResponse toCategoryAddResponse(Category category);

}
