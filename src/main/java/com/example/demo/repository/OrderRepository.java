package com.example.demo.repository;
import com.example.demo.model.Order;
import com.example.demo.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByCreatedDateBetween(LocalDateTime start, LocalDateTime end);


}
