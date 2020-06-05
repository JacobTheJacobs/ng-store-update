package com.example.demo;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootApplication
public class Demo9Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo9Application.class, args);
	}

	
	@Bean("gmail")
    public JavaMailSender gmailMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("*");
        mailSender.setPort(*);
        mailSender.setUsername("*");
        mailSender.setPassword("*");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "false");

        return mailSender;
    }
}
