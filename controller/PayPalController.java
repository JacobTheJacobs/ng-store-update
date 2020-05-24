package com.example.demo.controller;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Email;
import com.example.demo.model.PayPalClient;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/paypal")
public class PayPalController {

    private final PayPalClient payPalClient;
    @Autowired
    @Qualifier("gmail")
    private JavaMailSender mailSender;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    PayPalController(PayPalClient payPalClient){
        this.payPalClient = payPalClient;
    }

    @PostMapping(value = "/make/payment")
    public Map<String, Object> makePayment(@RequestParam("sum") String sum){
    	System.out.println(sum);
        return payPalClient.createPayment(sum);
    }
    
    @PostMapping(value = "/complete/payment")
    public Map<String, Object> completePayment(HttpServletRequest request,Principal principal){
    	sendMailToMeWhenUserBuy(principal);
    	sendMailToUserWhenBuy(principal);
        return payPalClient.completePayment(request);
        
    }
    
    private static final Logger logger = LoggerFactory.getLogger(PayPalController.class);

    
    public void sendMailToUserWhenBuy(Principal principal) {
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
           
            String userName= principal.getName();
            User userToEmail= userRepository.findByUsername(userName).get();
            String userEmail = userToEmail.getEmail();
            String userCountry = userToEmail.getBuyerCountry();
            
            message.setTo(userEmail);
            message.setFrom("Ethiopian@store.com");
            message.setSubject("Thanks you :)");
  
            message.setText("Thanks you for youore purchase");
        };
        mailSender.send(preparator);
        logger.info("Email sent successfully To {},{} with Subject {}");
    }
    
 
    public void sendMailToMeWhenUserBuy(Principal principal) {
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
           
            String userName= principal.getName();
            User userToEmail= userRepository.findByUsername(userName).get();
            String userEmail = userToEmail.getEmail();
            String userCountry = userToEmail.getBuyerCountry();
            
            message.setTo("jacobthejacobs@gmail.com");
            message.setFrom(userEmail);
            message.setSubject("Item sold!!!!");
  
            message.setText("User Email "+userEmail+" UserName "+userName+"just bought an Item"+"from "+userCountry);
        };
        mailSender.send(preparator);
        logger.info("Email sent successfully To {},{} with Subject {}");
    }
    
    @PostMapping(value = "/email")
    public void sendMailToMe(@RequestBody Email email,Principal principal) {
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
           
            String userName= principal.getName();
            User userToEmail= userRepository.findByUsername(userName).get();
            String userEmail = userToEmail.getEmail();
            
            message.setTo("jacobthejacobs@gmail.com");
            message.setFrom(userEmail);
            message.setSubject(email.getUserSubject());
  
            message.setText(email.getUserMessage()+"from "+userEmail+" name "+userName);
        };
        mailSender.send(preparator);
        logger.info("Email sent successfully To {},{} with Subject {}");
    }
}
