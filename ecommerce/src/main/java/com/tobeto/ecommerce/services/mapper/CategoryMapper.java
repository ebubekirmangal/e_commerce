package com.tobeto.ecommerce.services.mapper;

import com.tobeto.ecommerce.entities.Category;
import com.tobeto.ecommerce.services.dtos.requests.category.AddCategoryRequest;
import com.tobeto.ecommerce.services.dtos.requests.category.UpdateCategoryRequest;
import com.tobeto.ecommerce.services.dtos.responses.category.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    @Mapping(target = "parentCategory.id",source = "parentCategoryId")
    Category categoryFromAddRequest(AddCategoryRequest request);
//    @Mapping(target = "subCategory",source = "");
    AddCategoryResponse toCategoryAddResponse(Category category);
    @Mapping(target = "parentCategory.id",source = "parentCategoryId")
    Category categoryFromUpdateRequest(UpdateCategoryRequest request);

    UpdateCategoryResponse updateToCategoryDto(Category category);

    List<ListCategoryResponse> listToCategoryDto(List<Category> category);

    GetByIdCategoryResponse GetByIdToCategoryDto(Category category);

    DeleteCategoryResponse DeleteToCategoryDto(Category category);

    default List<String> mapSubCategoriesToStrings(List<Category> subCategories) {
        if (subCategories == null) {
            return null;
        }
        return subCategories.stream()
                .map(Category::getName)
                .collect(Collectors.toList());
    }

}
