package com.example.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.ProductStatusEnum;
import com.example.demo.model.Products;
import com.example.demo.model.ResultEnum;
import com.example.demo.repo.ProductRepository;
import com.example.demo.security.MyException;
import com.example.demo.service.impl.CategoryService;
import com.example.demo.service.impl.ProductService;



@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productInfoRepository;

    @Autowired
    CategoryService categoryService;
    

    @Override
    public Products findOne(Long productId) {
        Products productInfo = productInfoRepository.findById(productId).get();
        return productInfo;
    }
    
    @Override
    public List<Products> findUpAll() {
        return productInfoRepository.findAllByStatusOrderByIdAsc(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<Products> findAll() {
        return productInfoRepository.findAll();
    }

    @Override
    public List<Products> findAllInCategory(Integer categoryType) {
        return productInfoRepository.findAllByCategoryOrderByIdAsc(categoryType);
    }

    @Override
    @Transactional
    public void increaseStock(Long productId, int amount) {
    	Products productInfo = findOne(productId);
        if (productInfo == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);
        int update = productInfo.getQuantity() + amount;
        productInfo.setQuantity(update);
        productInfoRepository.save(productInfo);
    }

    @Override
    @Transactional
    public void decreaseStock(Long productId, int amount) {
    	Products productInfo = findOne(productId);
       if (productInfo == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);

        int update = productInfo.getQuantity() - amount;
       if(update <= 0) throw new MyException(ResultEnum.PRODUCT_NOT_ENOUGH );

        productInfo.setQuantity(update);
        productInfoRepository.save(productInfo);
    }

    @Override
    @Transactional
    public Products offSale(Long productId) {
        Products productInfo = findOne(productId);
       if (productInfo == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);

        if (productInfo.getStatus() == ProductStatusEnum.DOWN.getCode()) {
            throw new MyException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setStatus(ProductStatusEnum.DOWN.getCode());
        return productInfoRepository.save(productInfo);
    }

    @Override
    @Transactional
    public Products onSale(Long productId) {
    	Products productInfo = findOne(productId);
    	
        if (productInfo == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);

        if (productInfo.getStatus() == ProductStatusEnum.UP.getCode()) {
            throw new MyException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        
        productInfo.setStatus(ProductStatusEnum.UP.getCode());
        return productInfoRepository.save(productInfo);
    
    }

    
    
    @Override
    public Products update(Products productInfo) {

        // if null throw exception
        categoryService.findByCategoryType(productInfo.getCategory());
        if(productInfo.getStatus() > 1) {
           throw new MyException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        return productInfoRepository.save(productInfo);
    }

    @Override
    public Products save(Products productInfo) {
        return update(productInfo);
    }

    @Override
    public void delete(Long productId) {
    	Products productInfo = findOne(productId);
  //      if (productInfo == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);
        productInfoRepository.delete(productInfo);

    }

}