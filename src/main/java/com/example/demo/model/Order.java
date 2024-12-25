package com.example.demo.model;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "orders") // tablo adını orders yapalım karışıklık olmason diye
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order() {
        this.status=OrderStatus.PENDING; //varsayılan durum PENDING
    }

    @Column(name ="created_date")
    private LocalDateTime createdDate=LocalDateTime.now();

    public void addProduct(Product product) {

        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }
    public Long getId() {
        return id;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
