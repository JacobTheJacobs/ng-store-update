package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Some category
   // List<Category> findByCategoryIdOrderByCategoryTypeAsc(List<Integer> categoryTypes);
    // All category
  //  List<Category> findAllByOrderByCategoryType(Integer categoryType);
    // One category
    Category findByCategoryType(Integer categoryType);


}
