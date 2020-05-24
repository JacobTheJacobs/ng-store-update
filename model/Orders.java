package com.example.demo.model;


import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * OrderMain contains User info and products in the order
 * Created By Zhu Lin on 3/14/2018.
 */
@Entity
@DynamicUpdate
public class Orders implements Serializable {
    private static final long serialVersionUID = -3819883511505235030L;

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "orders")
    private Set<ProductInCart> products = new HashSet<>();

    @NotEmpty
    private String buyerEmail;

    @NotEmpty
    private String buyerName;

    @NotEmpty
    private String buyerPhone;

    @NotEmpty
    private String buyerCountry;
    
    @NotEmpty
    private String buyerCity;
    
    @NotEmpty
    private String zipCode;
    
    @NotEmpty
    private String buyerAddress;

    // Total Amount
    @NotNull
    private BigDecimal orderAmount;

    /**
     * default 0: new order.
     */
    @NotNull
    @ColumnDefault("0")
    private Integer orderStatus;

    @CreationTimestamp
    private LocalDateTime createTime;

    @UpdateTimestamp
    private LocalDateTime updateTime;
    


public Orders() {}

	public Orders(User buyer) {
        this.buyerEmail = buyer.getEmail();
		this.buyerName = buyer.getBuyerName();
		this.buyerPhone = buyer.getBuyerPhone();
		this.buyerCountry = buyer.getBuyerCountry();
		this.buyerCity = buyer.getBuyerCity();
		this.zipCode = buyer.getZipCode();
		this.buyerAddress = buyer.getBuyerAddress();
        this.orderAmount = buyer.getCart().getProducts().stream().map(item -> item.getProductPrice().multiply(new BigDecimal(item.getCount())))
                .reduce(BigDecimal::add)
                .orElse(new BigDecimal(0));
        this.orderStatus = 0;

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




	public Long getOrderId() {
		return orderId;
	}




	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}




	public Set<ProductInCart> getProducts() {
		return products;
	}




	public void setProducts(Set<ProductInCart> products) {
		this.products = products;
	}




	public String getBuyerEmail() {
		return buyerEmail;
	}




	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
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








	public BigDecimal getOrderAmount() {
		return orderAmount;
	}




	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}




	public Integer getOrderStatus() {
		return orderStatus;
	}




	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}




	public LocalDateTime getCreateTime() {
		return createTime;
	}




	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}




	public LocalDateTime getUpdateTime() {
		return updateTime;
	}




	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
