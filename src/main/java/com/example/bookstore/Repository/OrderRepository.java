package com.example.bookstore.Repository;

import com.example.bookstore.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>{

    List<Order> findByOrderByOrderDateAsc();
    List<Order> findByOrderByOrderDateDesc();
}
