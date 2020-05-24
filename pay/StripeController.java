package com.example.demo.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class StripeController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/stripe/payment")
    public ResponseEntity<String> payment(@RequestBody StripePayment paymentIntentDto) throws StripeException {
        PaymentIntent paymentIntent = paymentService.paymentIntent(paymentIntentDto);
        String paymentStr = paymentIntent.toJson();//convert string to json
        System.out.println(paymentStr);
    
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }

    @PostMapping("/stripe/confirm/{id}")
    public ResponseEntity<String> confirm(@PathVariable("id") String id) throws StripeException {
        PaymentIntent paymentIntent = paymentService.confirm(id);
        String paymentStr = paymentIntent.toJson();

        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }

    @PostMapping("/stripe/cancel/{id}")
    public ResponseEntity<String> cancel(@PathVariable("id") String id) throws StripeException {
        PaymentIntent paymentIntent = paymentService.cancel(id);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }
}