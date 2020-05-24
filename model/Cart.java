package com.example.demo.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.FetchMode;
import org.hibernate.annotations.Fetch;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cart {

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long  cartId;

	    @OneToOne(fetch = FetchType.LAZY)
	    @MapsId
	    //@JoinColumn(name = "user_id")
	    @JsonIgnore
	    private User user;

	    @OneToMany(cascade = CascadeType.ALL,
	            fetch = FetchType.LAZY, orphanRemoval = true,
	            mappedBy = "cart")
	    private Set<ProductInCart> products = new HashSet<>();

	   
	   
	public Cart() {
		
	}

	    public Cart(User user) {
	    	setCartId(user.getId());
	    	setUser(user);
	        this.user  = user;
	    }
	    
	    

		public Cart( User user, Set<ProductInCart> products) {
			this.user = user;
			this.products = products;
		}
		
		
		public Long getCartId() {
			return cartId;
		}

		public void setCartId(Long cartId) {
			this.cartId = cartId;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			
			this.user = user;
		}

		public Set<ProductInCart> getProducts() {
			return products;
		}

		public void setProducts(Set<ProductInCart> products) {
			this.products = products;
		}
		
		
	    
	    
}
