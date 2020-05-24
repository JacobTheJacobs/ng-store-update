package com.example.demo.controller;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class ItemForm {
    @Min(value = 1)
    private Integer quantity;
    @NotEmpty
    private Long productId;
    
    private String userId;
    
    
	
	
	public ItemForm(@Min(1) Integer quantity, @NotEmpty Long productId, String userId) {
		super();
		this.quantity = quantity;
		this.productId = productId;
		this.userId = userId;
	}
	
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
    
    
    
}
