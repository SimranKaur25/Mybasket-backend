/**
 * 
 */
package com.simran.ShoppingCart.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * @author Gursimran kaur
 *
 */
@Entity
@Table(name="user")
public class User {
	    @Id
	    @Column(name="user_id")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long userId;
	    
	    @Column(name="name",nullable=false)
	    private String name;
	    
	    
	    @Column(name = "mobile_number",nullable=false,unique=true)
	    private String mobileNumber;
	    
	    
	    @Column(name="address",nullable=false,columnDefinition="TEXT")
	    private String address;
	   
	    
		@Column(name="created_date",nullable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
		private Date createdDate;
	    
		
		@Column(name="password")
//		@NotEmpty(message=ApplicationConstant.MANDATORY_PARAM_NOT_FOUND)
//		@Size(min=6, message=ApplicationConstant.INVALID_PASSWORD)
         private String password;

	    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "userDetails")
	    private List<UserCart> userCart;

		public Long getUserId() {
			return userId;
		}


		public void setUserId(Long userId) {
			this.userId = userId;
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


		public Date getCreatedDate() {
			return createdDate;
		}


		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		@Override
		public String toString() {
			return "User [userId=" + userId + ", name=" + name + ", mobileNumber=" + mobileNumber + ", address="
					+ address + ", createdDate=" + createdDate + ", password=" + password + "]";
		}

	

}
