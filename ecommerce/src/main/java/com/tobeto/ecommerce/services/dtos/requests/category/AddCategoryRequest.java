package com.tobeto.ecommerce.services.dtos.requests.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCategoryRequest {

//    private int userId;

    private String name;

    private Boolean isActive;

    private int parentCategoryId;
}
