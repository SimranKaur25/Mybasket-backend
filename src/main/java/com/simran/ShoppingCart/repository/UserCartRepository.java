/**
 * 
 */
package com.simran.ShoppingCart.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.simran.ShoppingCart.entity.UserCart;

/**
 * @author Gursimran kaur
 *
 */
@Transactional
@Repository
public interface UserCartRepository extends JpaRepository<UserCart, Long>, UserCartRepositoryCustom{

	
	@Query(value= "SELECT  *  FROM  user_cart WHERE reference_userid = :userId  AND reference_categoryid = :categoryId LIMIT 1",nativeQuery=true)
	public UserCart findByReferenceUserIdAndReferenceCategoryId(@Param("userId")Long userId ,@Param("categoryId")Long categoryId);

	@Query(value= "SELECT  *  FROM  user_cart WHERE  reference_userid = :userId ",nativeQuery=true)
	public List<UserCart> findByReferenceUserId(@Param("userId")Long userId);
	
	@Modifying
	@Query(value= "DELETE    FROM  user_cart WHERE  reference_userid = :userId AND reference_categoryid = :categoryId ",nativeQuery=true)
	public Integer deleteByReferenceUserIdAndReferenceCategoryId(@Param("userId")Long userId ,@Param("categoryId")Long categoryId);

	@Modifying
	@Query(value= "DELETE    FROM  user_cart WHERE reference_userid = :userId ",nativeQuery=true)
	public Integer deleteByReferenceUserId(@Param("userId")Long userId);


	@Modifying
	@Query(value= "UPDATE   user_cart SET quantity = :quantity WHERE reference_userid = :userId AND reference_categoryid = :categoryId",nativeQuery=true)
	public Integer UpdateByReferenceUserIdAndReferenceCategoryId(@Param("userId")Long userId ,@Param("categoryId")Long categoryId,@Param("quantity")Long quantity);

	
}
