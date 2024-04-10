package com.tobeto.ecommerce.services.mapper;

import com.tobeto.ecommerce.entities.Category;
import com.tobeto.ecommerce.services.dtos.requests.Category.CategoryAddRequest;
import com.tobeto.ecommerce.services.dtos.responses.Category.CategoryAddResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    @Mapping(target = "parentCategory.id",source = "parentId")
    @Mapping(target = "subCategory.id",source = "subId")
    Category categoryFromAddRequest(CategoryAddRequest request);
    @Mapping(target = "parentName",source = "parentCategory.name")
    @Mapping(target = "subName",source = "subCategory.name")
    CategoryAddResponse toCategoryAddResponse(Category category);

}
