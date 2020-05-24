package com.example.demo.service.impl;

import java.util.List;

import com.example.demo.model.Orders;

public interface OrderService {
    List<Orders> findAll();

    List<Orders> findByStatus(Integer status);

    List<Orders> findByBuyerEmail(String email);



    Orders findOne(Long orderId);


    Orders finish(Long orderId);

    Orders cancel(Long orderId);

}
