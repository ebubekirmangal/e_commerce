package com.tobeto.ecommerce.services.dtos.responses.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoryResponse {

    private int id;

    private String name;

    private Boolean isActive;

    private List<String> subCategories;
}
