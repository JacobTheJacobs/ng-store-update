package com.example.demo.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ProductInCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Cart cart;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Orders orders;


    private Long productId;
    private String productName;
    private String productDescription;
    private String img;
    private Integer categoryType;
    private BigDecimal productPrice;
    @Min(0)
    private Integer productStock;
    @Min(1)
    private Integer count;
    
    public ProductInCart() {}


    public ProductInCart(Products productInfo, Integer quantity) {
        this.productId = productInfo.getId();
        this.productName = productInfo.getName();
        this.productDescription = productInfo.getDescription();
        this.img = productInfo.getImg();
        this.categoryType = productInfo.getCategory();
        this.productPrice = productInfo.getPrice();
        this.productStock = productInfo.getQuantity();
        this.count = quantity;
    }
    
    

 
    



	public Orders getOrders() {
		return orders;
	}


	public void setOrders(Orders orders) {
		this.orders = orders;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProductInCart that = (ProductInCart) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(productDescription, that.productDescription) &&
                Objects.equals(img, that.img) &&
                Objects.equals(categoryType, that.categoryType) &&
                Objects.equals(productPrice, that.productPrice);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, productId, productName, productDescription, img, categoryType, productPrice);
    }



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Cart getCart() {
		return cart;
	}



	public void setCart(Cart cart) {

		this.cart = cart;
	}



	public Long getProductId() {
		return productId;
	}



	public void setProductId(Long productId) {
		this.productId = productId;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public String getProductDescription() {
		return productDescription;
	}



	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}



	public String getProductIcon() {
		return img;
	}



	public void setProductIcon(String productIcon) {
		this.img = productIcon;
	}



	public Integer getCategoryType() {
		return categoryType;
	}



	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}



	public BigDecimal getProductPrice() {
		return productPrice;
	}



	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}



	public Integer getProductStock() {
		return productStock;
	}



	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}



	public Integer getCount() {
		return count;
	}



	public void setCount(Integer count) {
		this.count = count;
	}



}