package com.tobeto.ecommerce.services.dtos.responses.Category;

import java.util.List;

public class CategoryAddResponse {

    private int id;
    private String name;

    private Boolean isActive;

    private String parentName;

    private List<String> subName;
}
