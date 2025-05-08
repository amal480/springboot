package com.example.store.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

}
