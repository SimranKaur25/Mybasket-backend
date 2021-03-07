/**
 * 
 */
package com.simran.ShoppingCart.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.simran.ShoppingCart.entity.CategoryGeneric;

/**
 * @author Gursimran kaur
 *
 */
@Repository
@Transactional
public interface CategoryGenericRepository  extends JpaRepository<CategoryGeneric, Long>{

	
	
	@Transactional
	@Query(value= "SELECT * FROM category_generic WHERE  parent_id IS NULL AND is_active=true AND is_root=true  ORDER BY category_id",nativeQuery=true)
	public List<CategoryGeneric> findByParentIdAndIsActiveAndIsRoot();
	
	
	
	
	
}
