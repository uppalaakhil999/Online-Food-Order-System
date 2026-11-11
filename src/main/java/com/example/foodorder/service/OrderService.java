package com.example.foodorder.service;

import com.example.foodorder.exception.OrderNotFoundException;
import com.example.foodorder.model.Order;
import com.example.foodorder.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    public Order addOrder(Order order) {
        order.setOrderId(null);
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order newOrder) {
        Order order = getOrderById(id);
        order.setCustomerName(newOrder.getCustomerName());
        order.setFoodItem(newOrder.getFoodItem());
        order.setQuantity(newOrder.getQuantity());
        order.setPrice(newOrder.getPrice());
        return orderRepository.save(order);
    }

    public Order patchOrder(Long id, Map<String, Object> changes) {
        Order order = getOrderById(id);

        if (changes.containsKey("customerName")) {
            order.setCustomerName((String) changes.get("customerName"));
        }

        if (changes.containsKey("foodItem")) {
            order.setFoodItem((String) changes.get("foodItem"));
        }

        if (changes.containsKey("quantity")) {
            Number quantity = (Number) changes.get("quantity");
            order.setQuantity(quantity.intValue());
        }

        if (changes.containsKey("price")) {
            order.setPrice(new BigDecimal(changes.get("price").toString()));
        }

        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        Order order = getOrderById(id);
        orderRepository.delete(order);
    }

    public List<Order> searchByCustomer(String name) {
        return orderRepository.findByCustomerNameContainingIgnoreCase(name);
    }

    public List<Order> searchByFoodItem(String item) {
        return orderRepository.findByFoodItemContainingIgnoreCase(item);
    }
}
