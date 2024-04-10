package com.tobeto.ecommerce.services.dtos.responses.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCategoryResponse {

    private int id;
    private String name;

    private Boolean isActive;

}
