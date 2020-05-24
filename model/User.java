package com.example.demo.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;


	@Size(max = 120)
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore  // fix bi-direction toString() recursion problem
    private Cart cart;
    
    

    private String buyerName;


    private String buyerPhone;


    private String buyerCountry;
    

    private String buyerCity;
    

    private String zipCode;
    

    private String buyerAddress;


	public User() {
	}
	
	

	public User(String username, String email, String password, 
			String buyerName, String buyerCountry, String buyerCity, String buyerPhone, String zipCode, String buyerAdress) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.buyerName = buyerName;
		this.buyerCountry = buyerCountry;
		this.buyerCity = buyerCity;
		this.buyerPhone = buyerPhone;
		this.zipCode = zipCode;
		this.buyerAddress = buyerAdress;
		
	}



/*		signUpRequest.getBuyerName(),
					signUpRequest.getBuyerCountry(),
					signUpRequest.getBuyerCity(),
					signUpRequest.getBuyerPhone(),
					signUpRequest.getZipCode(),
					signUpRequest.getBuyerAdress());*/



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
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