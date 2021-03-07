/**
 * 
 */
package com.simran.ShoppingCart.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.simran.ShoppingCart.entity.CategoryGeneric;

/**
 * @author Gursimran kaur
 *
 */

public class CategoryModel {
	
	

    private Long categoryId;

    private String categoryName;
    

    private Boolean isRoot;
		
 	private List<Object> subCategories;
      
    

    private String description;
    

    private String categoryType;
    
  
    

    private String imageUrl;
    private Long price;




	public Long getPrice() {
		return price;
	}




	public void setPrice(Long price) {
		this.price = price;
	}




	public Long getCategoryId() {
		return categoryId;
	}




	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}




	public String getCategoryName() {
		return categoryName;
	}




	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}




	public Boolean getIsRoot() {
		return isRoot;
	}




	public void setIsRoot(Boolean isRoot) {
		this.isRoot = isRoot;
	}




	


	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public String getCategoryType() {
		return categoryType;
	}




	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}




	public String getImageUrl() {
		return imageUrl;
	}




	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}




	public List<Object> getSubCategories() {
		return subCategories;
	}




	public void setSubCategories(List<Object> subCategories) {
		this.subCategories = subCategories;
	}
    
    
    
   
   
 


}
