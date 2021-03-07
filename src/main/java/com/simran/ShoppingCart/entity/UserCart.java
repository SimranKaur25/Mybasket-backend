/**
 * 
 */
package com.simran.ShoppingCart.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Gursimran kaur
 *
 */
@Entity
@Table(name="user_cart",uniqueConstraints = { @UniqueConstraint( columnNames = { "reference_categoryid", "reference_userid" } ) } )
public class UserCart {
	    @Id
	    @Column(name="cart_id")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long cartId;
	
	    
	    @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	    @JoinColumn(name = "reference_userid", referencedColumnName = "user_id")
	    private User userDetails;
	    
	    
	    @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	    @JoinColumn(name = "reference_categoryid", referencedColumnName = "category_id")
	    private CategoryGeneric categoryDetails;
	    
	    @Column(name="quantity")
	    private Long quantity;

		public Long getCartId() {
			return cartId;
		}

		public void setCartId(Long cartId) {
			this.cartId = cartId;
		}

		public User getUserDetails() {
			return userDetails;
		}

		public void setUserDetails(User userDetails) {
			this.userDetails = userDetails;
		}

		public CategoryGeneric getCategoryDetails() {
			return categoryDetails;
		}

		public void setCategoryDetails(CategoryGeneric categoryDetails) {
			this.categoryDetails = categoryDetails;
		}

		public Long getQuantity() {
			return quantity;
		}

		public void setQuantity(Long quantity) {
			this.quantity = quantity;
		}
	    
	    
	    
	    
	 

	
	    
	 
	

}
