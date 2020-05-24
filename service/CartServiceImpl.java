package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.demo.model.Cart;
import com.example.demo.model.Orders;
import com.example.demo.model.ProductInCart;
import com.example.demo.model.User;
import com.example.demo.repo.CartRepository;
import com.example.demo.repo.OrdersRepository;
import com.example.demo.repo.ProductInCartRepository;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.impl.CartService;
import com.example.demo.service.impl.ProductService;


@Service
public class CartServiceImpl implements CartService {
    @Autowired
    ProductService productService;
    
   @Autowired
   OrdersRepository orderRepository;
    
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductInCartRepository productInOrderRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userService;

    @Override
    public Cart getCart(User user) {
        return user.getCart();
    }

    @Override
    @Transactional
    public void mergeLocalCart(Collection<ProductInCart> productInOrders, User user) {
    	
        Cart finalCart = user.getCart();
        System.out.println(finalCart.getUser());
        System.out.println(user.getCart());
        
        

        System.out.println(finalCart);
        productInOrders.forEach(productInOrder -> {
            Set<ProductInCart> set = finalCart.getProducts();
            Optional<ProductInCart> old = set.stream().filter(e -> e.getProductId().equals(productInOrder.getProductId())).findFirst();
            ProductInCart prod;
          
            if (old.isPresent()) {
                prod = old.get();
                prod.setCount(productInOrder.getCount() + prod.getCount());
            } else {
                prod = productInOrder;
                prod.setCart(finalCart);
                finalCart.getProducts().add(prod);
            }
            productInOrderRepository.save(prod);
        });
        cartRepository.save(finalCart);

    }

    @Override
    @Transactional
    public void delete(Long itemId, User user) {
        Optional<ProductInCart> op = user.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
        op.ifPresent(productInOrder -> {
            productInOrder.setCart(null);
            productInOrderRepository.deleteById(productInOrder.getId());
        });
    }



    @Override
    @Transactional
    public void checkout(User user) {
        // Creat an order
    	
        Orders order = new Orders(user);
        orderRepository.save(order);

        // clear cart's foreign key & set order's foreign key& decrease stock
        user.getCart().getProducts().forEach(productInOrder -> {
            productInOrder.setCart(null);
            productInOrder.setOrders(order);
            productService.decreaseStock(productInOrder.getProductId(), productInOrder.getCount());
            productInOrderRepository.save(productInOrder);
        });
        

    }





	

}
