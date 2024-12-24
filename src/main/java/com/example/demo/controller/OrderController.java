package com.example.demo.controller;
import com.example.demo.model.Order;
import com.example.demo.model.OrderStatus;
import com.example.demo.model.Product;
import com.example.demo.repository.OrderRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api/order-management")
public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController( OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public String createOrder(@RequestBody List<Product> products){
        Order order = new Order();
        products.forEach(order::addProduct);
        orderRepository.save(order);
        return "Order created with total price: "+
                products.stream().mapToDouble(Product::getPrice).sum();
    }

    @PutMapping("/{id}")
    public String updateOrder(@PathVariable Long id, @RequestBody List<Product> products){
        return orderRepository.findById(id).map(
                order -> {
                    order.getProducts().clear(); //ürün temizliği
                    products.forEach(order::addProduct); //yeni ürün ekleme
                    orderRepository.save(order); // yeni siparişi kaydetme
                    return  "Order updated with id " +id + "has been updated ";

                }
        )
                .orElse("Order with id " + id + " not found");

    }


    @PutMapping("/{id}/status")
    public String updateOrderStatus(@PathVariable Long id,@RequestParam OrderStatus status){
        return orderRepository.findById(id)
                .map(order -> {
                    order.setStatus(status); //durumu günceller
                    orderRepository.save(order); // veri tabanına kayıt eder.
                    return  "Order status updated to:  " +status + "for Order with id " + id + " has been updated ";

                })
                .orElse("Order with id " + id + " not found");

    }




    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Long id){
        return orderRepository.findById(id)
                .map(order -> {
                    orderRepository.delete(order); //sipariş siliniyor
                    return  "Order with id " +id + "has been deleted";

                })
                .orElse("Order with id " + id + " not found");

    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

}
