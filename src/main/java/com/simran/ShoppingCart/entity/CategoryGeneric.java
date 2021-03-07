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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * @author Gursimran kaur
 *
 */
@Entity
@Table(name="category_generic")
public class CategoryGeneric {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="category_id")
    private Long categoryId;
	
    @Column(name="category_name")
    private String categoryName;
    
    @Column(name="price")
    private Long price;
    
	@Column(name="is_root",nullable=false,columnDefinition = "boolean default false")
    private Boolean isRoot;
	
    @ManyToOne
  	@JoinColumn(name = "parent_id")
  	private CategoryGeneric parent;
		
 	@OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
	private List<CategoryGeneric> children;
      
    
    
    @Column(name="description")
    private String description;
    
    @Column(name="category_type",nullable=false)
    private String categoryType;
    
  
    
    @Column(name="image_url")
    private String imageUrl;
    
   
    @Column(name="creation_date",nullable=false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date creationDate;
  
    
    
    
    @Column(name="is_active",nullable=false,columnDefinition = "boolean default true")
    private Boolean isActive;
   
    @Column(name="show_children",nullable=false,columnDefinition = "boolean default false")
    private Boolean showChildren;
    
    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "categoryDetails")
    private List<UserCart> userCart;
    
    
    

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

	public List<CategoryGeneric> getChildren() {
		return children;
	}

	public void setChildren(List<CategoryGeneric> children) {
		this.children = children;
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getShowChildren() {
		return showChildren;
	}

	public void setShowChildren(Boolean showChildren) {
		this.showChildren = showChildren;
	}
	

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public CategoryGeneric getParent() {
		return parent;
	}

	public void setParent(CategoryGeneric parent) {
		this.parent = parent;
	}

	public List<UserCart> getUserCart() {
		return userCart;
	}

	public void setUserCart(List<UserCart> userCart) {
		this.userCart = userCart;
	}

	@Override
	public String toString() {
		return "CategoryGeneric [categoryId=" + categoryId + ", categoryName=" + categoryName + ", isRoot=" + isRoot
				+ ", description=" + description + ", categoryType=" + categoryType + ", imageUrl=" + imageUrl
				+ ", creationDate=" + creationDate + ", isActive=" + isActive + ", showChildren=" + showChildren + "]";
	}

	
       
 
    

}
