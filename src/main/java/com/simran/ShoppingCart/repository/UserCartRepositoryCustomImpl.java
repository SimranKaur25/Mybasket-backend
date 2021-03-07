/**
 * 
 */
package com.simran.ShoppingCart.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.simran.ShoppingCart.entity.UserCart;

/**
 * @author Gursimran kaur
 *
 */
public class UserCartRepositoryCustomImpl implements UserCartRepositoryCustom {

	@PersistenceContext
	   private EntityManager em;
	   @Override
	   @Transactional
	   public void refresh(UserCart userCart) {
	      em.refresh(userCart);
	   }
}
