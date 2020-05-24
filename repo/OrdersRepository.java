package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    Orders findByOrderId(Long orderId);


    List<Orders> findAllByOrderStatusOrderByCreateTimeDesc(Integer orderStatus);


    List<Orders> findAllByBuyerEmailOrderByOrderStatusAscCreateTimeDesc(String buyerEmail);

    List<Orders> findAllByOrderByOrderStatusAscCreateTimeDesc();

   
}