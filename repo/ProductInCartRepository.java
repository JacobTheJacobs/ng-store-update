package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ProductInCart;



@Repository
public interface ProductInCartRepository extends JpaRepository<ProductInCart, Long> {

}

