package com.tobeto.ecommerce.services.dtos.requests.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryRequest {

//    private int userId;

    private int id;

    private String name;

    private Boolean isActive;

    private int parentCategoryId;
}
