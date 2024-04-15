package com.tobeto.ecommerce.repositories;

import com.tobeto.ecommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
Optional<Category> findByNameIgnoreCase(String name);
}
