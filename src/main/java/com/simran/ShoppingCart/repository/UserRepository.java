/**
 * 
 */
package com.simran.ShoppingCart.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simran.ShoppingCart.entity.User;

/**
 * @author Gursimran kaur
 *
 */
@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long> {


	public User findByMobileNumber(String mobileNumber);
	
	
}
