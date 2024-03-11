package com.example.bookstore.Service;

import com.example.bookstore.Model.Order;
import com.example.bookstore.Model.Book;
import com.example.bookstore.Model.User;
import com.example.bookstore.Repository.BookRepository;
import com.example.bookstore.Repository.OrderRepository;
import com.example.bookstore.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;



    @Autowired
    public OrderService(OrderRepository orderRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }



    public Order createOrder(Order order) {
        Optional<Book> optionalBook = bookRepository.findById(order.getBookID());
        Optional<User> optionalUser = userRepository.findById(order.getUser().getId());

        if (optionalBook.isPresent() && optionalUser.isPresent()) {
            Book orderedBook = optionalBook.get();
            User user = optionalUser.get();

            double bookPrice = orderedBook.getPrice();
            double totalPrice = bookPrice * order.getQuantity();
            order.setTotalPrice(totalPrice);

            int orderedQuantity = order.getQuantity();
            int currentStock = orderedBook.getStock();

            if (currentStock >= orderedQuantity) {
                orderedBook.setStock(currentStock - orderedQuantity);
                bookRepository.save(orderedBook);
                order.setUser(user);
                return orderRepository.save(order);
            }
            else {
                throw new IllegalArgumentException("The order could not be created. Insufficient stock.");
            }
        }
        else {
            throw new IllegalArgumentException("The order could not be created. Invalid user or book ID.");
        }
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
    public Order updateOrder(Long id, Order updatedOrder) {
        if (orderRepository.existsById(id)) {
            updatedOrder.setId(id);
            return orderRepository.save(updatedOrder);
        }
        return null;
    }

    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Order> getOrdersByDateAscending() {
        return orderRepository.findByOrderByOrderDateAsc();
    }

    public List<Order> getOrdersByDateDescending() {
        return orderRepository.findByOrderByOrderDateDesc();
    }

}
