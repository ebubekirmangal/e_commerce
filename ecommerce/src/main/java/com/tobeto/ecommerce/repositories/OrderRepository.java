package com.tobeto.ecommerce.repositories;

import com.tobeto.ecommerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Order,Integer> {
}
