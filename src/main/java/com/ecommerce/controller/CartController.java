package com.ecommerce.controller;

import com.ecommerce.model.Order;
import com.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order addToCart(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    @DeleteMapping("/{id}")
    public String removeFromCart(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ("Remove item from cart successfully");
    }
}
