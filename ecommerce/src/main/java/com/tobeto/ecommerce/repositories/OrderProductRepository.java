package com.tobeto.ecommerce.repositories;

import com.tobeto.ecommerce.entities.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct,Integer> {
}
