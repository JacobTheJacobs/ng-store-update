package com.example.demo.service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.ProductInCart;
import com.example.demo.model.User;
import com.example.demo.repo.ProductInCartRepository;
import com.example.demo.service.impl.ProductInCartService;



@Service
public class ProductInCartServiceImpl implements ProductInCartService {

    @Autowired
    ProductInCartRepository productInOrderRepository;

    @Override
    @Transactional
    public void update(Long itemId, Integer quantity, User user) {
        Optional<ProductInCart> op = user.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
        op.ifPresent(productInOrder -> {
            productInOrder.setCount(quantity);
            productInOrderRepository.save(productInOrder);
        });

    }

    @Override
    public ProductInCart findOne(Long itemId, User user) {
        Optional<ProductInCart> op = user.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
        AtomicReference<ProductInCart> res = new AtomicReference<>();
        op.ifPresent(res::set);
        return res.get();
    }




}