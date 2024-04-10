package com.tobeto.ecommerce.services.dtos.requests.Category;

import com.tobeto.ecommerce.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryAddRequest {

    private String name;

    private Boolean isActive;

    private int parentId;

    private List<Integer> subId;
}
