package com.example.demo.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Orders;
import com.example.demo.model.ProductInCart;
import com.example.demo.service.impl.OrderService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class OrderController {
    @Autowired
    OrderService orderService;
 

    @GetMapping("/order")
    public List<Orders> orderList(Authentication authentication) {
    	System.out.println(authentication.getName());
        List<Orders> orderPage;
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CUSTOMER"))) {
            orderPage = orderService.findByBuyerEmail(authentication.getName());
        } else {
            orderPage = orderService.findAll();
        }
        return orderPage;
    }


    @PatchMapping("/order/cancel/{id}")
    public ResponseEntity<Orders> cancel(@PathVariable("id") Long orderId, Authentication authentication) {
        Orders orderMain = orderService.findOne(orderId);
        if (!authentication.getName().equals(orderMain.getBuyerEmail()) && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CUSTOMER"))) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(orderService.cancel(orderId));
    }

    @PatchMapping("/order/finish/{id}")
    public ResponseEntity<Orders> finish(@PathVariable("id") Long orderId, Authentication authentication) {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CUSTOMER"))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(orderService.finish(orderId));
    }

    @GetMapping("/order/{id}")
    public ResponseEntity show(@PathVariable("id") Long orderId, Authentication authentication) {
        boolean isCustomer = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
        Orders orderMain = orderService.findOne(orderId);
        if (isCustomer && !authentication.getName().equals(orderMain.getBuyerEmail())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Collection<ProductInCart> items = orderMain.getProducts();
        return ResponseEntity.ok(orderMain);
    }
}
