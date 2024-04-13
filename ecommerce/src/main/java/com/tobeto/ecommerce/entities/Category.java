package com.tobeto.ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    public Category(List<Product> products) {
        this.products = products;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "is_active")
    private Boolean isActive;
    
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    @OneToMany(mappedBy = "parentCategory")
    private List<Category> subCategories;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id",nullable = true)
    private Category parentCategory;


}
