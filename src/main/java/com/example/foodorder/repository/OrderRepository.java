package com.example.foodorder.repository;

import com.example.foodorder.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerNameContainingIgnoreCase(String customerName);

    List<Order> findByFoodItemContainingIgnoreCase(String foodItem);
}
