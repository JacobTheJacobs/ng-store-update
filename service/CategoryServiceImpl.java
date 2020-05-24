package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Category;
import com.example.demo.model.ResultEnum;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.security.MyException;
import com.example.demo.service.impl.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository productCategoryRepository;



    @Override
    public List<Category> findAll() {
        List<Category> res = productCategoryRepository.findAll();
      //  res.sort(Comparator.comparing(ProductCategory::getCategoryType));
        return res;
    }

    @Override
    public Category findByCategoryType(Integer categoryType) {
    	Category res = productCategoryRepository.findByCategoryType(categoryType);
        if(res == null) throw new MyException(ResultEnum.CATEGORY_NOT_FOUND);
        return res;
    }



    @Override
    @Transactional
    public Category save(Category productCategory) {
        return productCategoryRepository.save(productCategory);
    }
}
