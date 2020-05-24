package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.demo.model.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

    // product in one category
  //  List<Product> findAllByCategoryTypeOrderByIdAsc(Integer categoryType, Long id);

	  
	    // onsale product
	    List<Products> findAllByStatusOrderByIdAsc(Integer productStatus);

	    // product in one category
	    List<Products> findAllByCategoryOrderByIdAsc(Integer categoryType);

	  

}
