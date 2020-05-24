package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Products;
import com.example.demo.repo.ProductRepository;
import com.example.demo.service.impl.ProductService;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class ProductController {
   
    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/product")
    public List<Products> findAll() {
        return productRepository.findAll();
    }

    @GetMapping("/product/{productId}")
    public Products showOne(@PathVariable("productId") Long productId) {
        Products productInfo = productService.findOne(productId);

//        // Product is not available
//        if (productInfo.getProductStatus().equals(ProductStatusEnum.DOWN.getCode())) {
//            productInfo = null;
//        }

        return productInfo;
    }

    @PostMapping("/seller/product/new")
    public ResponseEntity create(@Valid @RequestBody Products product,
                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        System.out.println(product.getStatus());
        return ResponseEntity.ok(productService.save(product));
    }

    @PutMapping("/seller/product/{productId}")
    public ResponseEntity edit(@PathVariable("productId") Long productId,
                               @Valid @RequestBody Products product,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        if (!productId.equals(product.getId())) {
            return ResponseEntity.badRequest().body("Id Not Matched");
        }

        return ResponseEntity.ok(productService.update(product));
    }
    

    @DeleteMapping("/seller/product/{productId}")
    public ResponseEntity delete(@PathVariable("productId") Long productId) {
        productRepository.deleteById(productId);
        return ResponseEntity.ok().build();
    }

}