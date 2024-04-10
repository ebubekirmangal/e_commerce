package com.tobeto.ecommerce.services.dtos.responses.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCategoryResponse {

    private int id;
    private String name;

    private Boolean isActive;
}