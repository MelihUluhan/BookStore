package com.example.bookstore.Controller;

import com.example.bookstore.Model.Order;
import com.example.bookstore.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookstore/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Optional<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public Order createBook(@RequestBody Order orderRequest) {
       return orderService.createOrder(orderRequest);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        return orderService.updateOrder(id, updatedOrder);
    }

    @DeleteMapping("/{id}")
    public boolean deleteOrder(@PathVariable Long id) {
        return orderService.deleteOrder(id);
    }

    @GetMapping("/asc-date")
    public List<Order> getOrdersByDateAscending() {
        return orderService.getOrdersByDateAscending();
    }

    @GetMapping("/desc-date")
    public List<Order> getOrdersByDateDescending() {
        return orderService.getOrdersByDateDescending();
    }
}
