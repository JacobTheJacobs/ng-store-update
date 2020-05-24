package com.example.demo.service.impl;

import java.util.List;



import com.example.demo.model.Category;



public interface CategoryService {

    List<Category> findAll();

    Category findByCategoryType(Integer categoryType);



    Category save(Category productCategory);


}