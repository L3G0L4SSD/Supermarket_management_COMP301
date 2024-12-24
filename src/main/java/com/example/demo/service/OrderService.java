package com.example.demo.service;
import com.example.demo.model.Order;
import com.example.demo.model.Product;
import org.springframework.stereotype.Service;
@Service
public class OrderService {

    public double calculateTotalPrice(Order order) {
        return order.getProducts().stream().mapToDouble(Product::getPrice).sum();
    }




}
