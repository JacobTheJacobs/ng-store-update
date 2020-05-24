package com.example.demo.payload;


import java.util.Set;

import javax.validation.constraints.*;
 
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    private Set<String> role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    
 
    private String buyerName;


    private String buyerPhone;


    private String buyerCountry;
    

    private String buyerCity;
    

    private String zipCode;
    

    private String buyerAddress;


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Set<String> getRole() {
		return role;
	}


	public void setRole(Set<String> role) {
		this.role = role;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getBuyerName() {
		return buyerName;
	}


	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}


	public String getBuyerPhone() {
		return buyerPhone;
	}


	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}


	public String getBuyerCountry() {
		return buyerCountry;
	}


	public void setBuyerCountry(String buyerCountry) {
		this.buyerCountry = buyerCountry;
	}


	public String getBuyerCity() {
		return buyerCity;
	}


	public void setBuyerCity(String buyerCity) {
		this.buyerCity = buyerCity;
	}


	public String getZipCode() {
		return zipCode;
	}


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}


	public String getBuyerAddress() {
		return buyerAddress;
	}


	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}
  
    
    }