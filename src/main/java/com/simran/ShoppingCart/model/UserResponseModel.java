/**
 * 
 */
package com.simran.ShoppingCart.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.simran.ShoppingCart.entity.UserCart;

/**
 * @author Gursimran kaur
 *
 */
@JsonInclude(Include.NON_NULL)
public class UserResponseModel {

	
    private String name;
    private String mobileNumber;
    private String address;
    private List<Object> categories;
    private List<UserCart> cartDetails;
    
    String deliveryDate;
    private String orderId;
    private Long totalAmount;
    
    
	public Long getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}
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
	public List<Object> getCategories() {
		return categories;
	}
	public void setCategories(List<Object> categories) {
		this.categories = categories;
	}
	
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public List<UserCart> getCartDetails() {
		return cartDetails;
	}
	public void setCartDetails(List<UserCart> cartDetails) {
		this.cartDetails = cartDetails;
	}
	
	
    
    
    
   
     
}
