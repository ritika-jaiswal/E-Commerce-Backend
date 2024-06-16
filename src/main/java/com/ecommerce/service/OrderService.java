package com.ecommerce.service;

import com.ecommerce.exception.OrderNotFoundException;
import com.ecommerce.exception.OrderValidationException;
import com.ecommerce.model.Order;
import com.ecommerce.model.OrderItem;
import com.ecommerce.model.User;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found with id " + id));
    }

    public Order saveOrder(Order order) {
        if (order.getOrderItems() == null || order.getOrderItems().isEmpty()) {
            throw new OrderValidationException("Order must contain at least one order item");
        }

        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrder(order);
        }

        return orderRepository.save(order);
    }

    public Order updateOrder(Long orderId, Order updatedOrder) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id " + orderId));

        existingOrder.setStatus(updatedOrder.getStatus());
        existingOrder.setUser(updatedOrder.getUser());

        existingOrder.setShippingAddress(updatedOrder.getShippingAddress());
        existingOrder.setShippingCity(updatedOrder.getShippingCity());
        existingOrder.setShippingState(updatedOrder.getShippingState());
        existingOrder.setShippingZip(updatedOrder.getShippingZip());
        existingOrder.setShippingCountry(updatedOrder.getShippingCountry());
        existingOrder.setContactNo((updatedOrder.getContactNo()));

        existingOrder.getOrderItems().clear();
        for (OrderItem updatedOrderItem : updatedOrder.getOrderItems()) {
            updatedOrderItem.setOrder(existingOrder);
            existingOrder.getOrderItems().add(updatedOrderItem);
        }

        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("Order not found with id " + id);
        }
        orderRepository.deleteById(id);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByEmail(username);
    }
}
