package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@DynamicUpdate
public class Category implements Serializable {
	
    @Id
    @GeneratedValue
    private Integer categoryId;
    private String categoryName;
    private Integer categoryType;
    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;


    public Category() {
    }

    public Category(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
    
}