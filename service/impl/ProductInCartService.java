package com.example.demo.service.impl;

import com.example.demo.model.ProductInCart;
import com.example.demo.model.User;

public interface ProductInCartService {
	
    void update(Long itemId, Integer quantity, User user);
    
    ProductInCart findOne(Long itemId, User user);

}
