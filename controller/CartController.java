package com.example.demo.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Cart;
import com.example.demo.model.ProductInCart;
import com.example.demo.model.Products;
import com.example.demo.model.User;
import com.example.demo.repo.CartRepository;
import com.example.demo.repo.ProductInCartRepository;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.UserDetailsImpl;
import com.example.demo.service.impl.CartService;
import com.example.demo.service.impl.ProductInCartService;
import com.example.demo.service.impl.ProductService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    UserRepository userService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductInCartService productInOrderService;
    @Autowired
    ProductInCartRepository productInOrderRepository;
    
    @Autowired
    CartRepository cartRepository;
    
    //null somewhere

    @PostMapping("/cart")
    public ResponseEntity<Cart> mergeCart(@RequestBody Collection<ProductInCart> productInOrders, Principal principal) {
        User user = userService.findByUsername(principal.getName()).get();
        
        if(user.getCart()==null) {//for newly customer
        	System.out.println("user.getCArt==null");

            Cart preFinalCart = new Cart();
            preFinalCart.setCartId(user.getId());
            preFinalCart.setUser(user);
            System.out.println(preFinalCart);
            cartRepository.save(preFinalCart);
       
        }
        
        System.out.println(user);
        System.out.println(principal.getName());
        System.out.println(productInOrders);
        try {
        	
           cartService.mergeLocalCart(productInOrders, user);
        	
        	
        } catch (Exception e) {
            ResponseEntity.badRequest().body("Merge Cart Failed");
            System.out.println("merge failed");
        }
        return ResponseEntity.ok(cartService.getCart(user));
    }
    

    @GetMapping("/cart")
    public Cart getCart(Principal principal) {
    	 if (principal == null || principal.getName() == null) {
             System.out.println(principal);
             System.out.println(principal.getName());
         }
        User user = userService.findByUsername(principal.getName()).get();
        return cartService.getCart(user);
    }

   
    @PostMapping("/cart/add")
    public boolean addToCart(@RequestBody ItemForm form, Principal name) {
        Products productInfo = productService.findOne(form.getProductId());
        

        ProductInCart newItem = new ProductInCart(productInfo, form.getQuantity());

        try {
            mergeCart(Collections.singleton(newItem), name);
            
            System.out.println("OK");
        } catch (Exception e) {
        	 System.out.println("Wrong null");
        	 
            return false;
        }
        return true;
    }
    


    @PutMapping("/cart/{itemId}")
    public ProductInCart modifyItem(@PathVariable("itemId") Long itemId, @RequestBody Integer quantity, Principal principal) {
        User user = userService.findByUsername(principal.getName()).get();
        
         productInOrderService.update(itemId, quantity, user);
        return productInOrderService.findOne(itemId, user);
    }

    @DeleteMapping("/cart/{itemId}")
    public void deleteItem(@PathVariable("itemId") Long itemId, Principal principal) {
        User user = userService.findByUsername(principal.getName()).get();
         cartService.delete(itemId, user);
         // flush memory into DB
    }


    @PostMapping("/checkout")
    public ResponseEntity checkout(Principal principal) {
        User user = userService.findByUsername(principal.getName()).get();// Email as username
        System.out.println(user);
        cartService.checkout(user);
        return ResponseEntity.ok(null);
    }

}
