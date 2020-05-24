package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.OrderStatusEnum;
import com.example.demo.model.Orders;
import com.example.demo.model.ProductInCart;
import com.example.demo.model.Products;
import com.example.demo.model.ResultEnum;
import com.example.demo.repo.OrdersRepository;
import com.example.demo.repo.ProductInCartRepository;
import com.example.demo.repo.ProductRepository;
import com.example.demo.repo.UserRepository;
import com.example.demo.security.MyException;
import com.example.demo.service.impl.OrderService;
import com.example.demo.service.impl.ProductService;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrdersRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productInfoRepository;
    @Autowired
    ProductService productService;
    @Autowired
    ProductInCartRepository productInOrderRepository;

    @Override
    public List<Orders> findAll() {
        return orderRepository.findAllByOrderByOrderStatusAscCreateTimeDesc();
    }

    @Override
    public List<Orders> findByStatus(Integer status) {
        return orderRepository.findAllByOrderStatusOrderByCreateTimeDesc(status);
    }

    @Override
    public List<Orders> findByBuyerEmail(String email) {
        return orderRepository.findAllByBuyerEmailOrderByOrderStatusAscCreateTimeDesc(email);
    }

    

    @Override
    public Orders findOne(Long orderId) {
    	Orders orderMain = orderRepository.findByOrderId(orderId);
        if(orderMain == null) {
            throw new MyException(ResultEnum.ORDER_NOT_FOUND);
        }
        return orderMain;
    }

    @Override
    @Transactional
    public Orders finish(Long orderId) {
    	Orders orderMain = findOne(orderId);
        if(!orderMain.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new MyException(ResultEnum.ORDER_STATUS_ERROR);
        }

        orderMain.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderRepository.save(orderMain);
        return orderRepository.findByOrderId(orderId);
    }

    @Override
    @Transactional
    public Orders cancel(Long orderId) {
    	Orders orderMain = findOne(orderId);
        if(!orderMain.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new MyException(ResultEnum.ORDER_STATUS_ERROR);
        }

        orderMain.setOrderStatus(OrderStatusEnum.CANCELED.getCode());
        orderRepository.save(orderMain);

        // Restore Stock
        Iterable<ProductInCart> products = orderMain.getProducts();
        for(ProductInCart productInOrder : products) {
            Products productInfo = productInfoRepository.findById(productInOrder.getProductId()).get();
            if(productInfo != null) {
                productService.increaseStock(productInOrder.getProductId(), productInOrder.getCount());
            }
        }
        return orderRepository.findByOrderId(orderId);

    }
}