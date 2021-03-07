/**
 * 
 */
package com.simran.ShoppingCart.model;

import java.util.Date;

import javax.persistence.Column;

/**
 * @author Gursimran kaur
 *
 */
public class UserModel {

    

    private String name;
    
    

    private String mobileNumber;
    
    private Long categoryId;
    
    private Long quantity;
    
    

    private String address;
   
     private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "UserModel [name=" + name + ", mobileNumber=" + mobileNumber + ", categoryId=" + categoryId
				+ ", quantity=" + quantity + ", address=" + address + ", password=" + password + "]";
	}

	

	
     
     

}
