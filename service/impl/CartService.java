package com.example.demo.service.impl;

import java.util.Collection;


import com.example.demo.model.Cart;
import com.example.demo.model.ProductInCart;
import com.example.demo.model.User;



public interface CartService {
    Cart getCart(User user);

    void mergeLocalCart(Collection<ProductInCart> productInOrders, User user);



    void checkout(User user);

	void delete(Long itemId, User user);

}