package com.example.demo.service.impl;

import java.util.List;


import com.example.demo.model.Products;

public interface ProductService {

    Products findOne(Long productId);

    // All selling products
    List<Products> findUpAll();
    // All products
    List<Products> findAll();
    // All products in a category
    List<Products> findAllInCategory(Integer categoryType);
    // increase stock
    void increaseStock(Long productId, int amount);

    //decrease stock
    void decreaseStock(Long productId, int amount);

    Products offSale(Long productId);

    Products onSale(Long productId);

    Products update(Products productInfo);
    Products save(Products productInfo);

    void delete(Long productId);


}