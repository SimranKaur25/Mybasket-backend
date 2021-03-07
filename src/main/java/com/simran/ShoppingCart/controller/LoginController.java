/**
 * 
 */
package com.simran.ShoppingCart.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.simran.ShoppingCart.entity.CategoryGeneric;
import com.simran.ShoppingCart.entity.User;
import com.simran.ShoppingCart.entity.UserCart;
import com.simran.ShoppingCart.model.CategoryModel;
import com.simran.ShoppingCart.model.UserModel;
import com.simran.ShoppingCart.model.UserResponseModel;
import com.simran.ShoppingCart.repository.CategoryGenericRepository;
import com.simran.ShoppingCart.repository.UserCartRepository;
import com.simran.ShoppingCart.repository.UserRepository;
import com.simran.ShoppingCart.utils.ApplicationConstant;
import com.simran.ShoppingCart.utils.CommonResponseEntity;
import com.simran.ShoppingCart.utils.ConstantVariables;
import com.simran.ShoppingCart.utils.PasswordUtils;
import com.simran.ShoppingCart.utils.ResponseCode;

/**
 * @author Gursimran kaur
 *
 */
@RestController
public class LoginController {

	@Autowired
	CommonResponseEntity commonResponseEntity;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CategoryGenericRepository categoryGenericRepository;

	@Autowired
	UserCartRepository userCartRepository;

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@PostMapping("/verifyUser")
	public LinkedHashMap<String, Object> findUser(@RequestBody UserModel userModel, HttpServletRequest request) {
		logger.info("inside verify user controller" + userModel);

		try {

			User user = userRepository.findByMobileNumber(userModel.getMobileNumber());
			logger.info("user" + user);
			if (user != null) {
				UserResponseModel userResponseModel = new UserResponseModel();
				userResponseModel.setMobileNumber(userModel.getMobileNumber());
				userResponseModel.setName(user.getName());

				return commonResponseEntity.ResponseEntityWithMapAndData(ResponseCode.SUCCESS_CODE,
						ConstantVariables.USER_FOUND, userResponseModel, ResponseCode.SUCCESS_CODE);
			} else {
				return commonResponseEntity.ResponseEntityWithMapAndData(ResponseCode.NO_DATA_FOUND,
						ConstantVariables.USER_NOT_FOUND, null, ResponseCode.NO_DATA_FOUND);
			}

		} catch (Exception e) {
			return commonResponseEntity.ResponseEntityForException();
		}

	}

	@PostMapping("/registerUser")
	public LinkedHashMap<String, Object> registerUser(@RequestBody UserModel userModel, HttpServletRequest request) {
		logger.info("inside registerUser controller" + userModel);

	//	try {

			User user = userRepository.findByMobileNumber(userModel.getMobileNumber());
			logger.info("user" + user);
			if (user != null) {
				logger.info("user already exists");

				return commonResponseEntity.ResponseEntityWithMapAndData(ResponseCode.DUPLICATE_USER,
						ConstantVariables.ACCOUNT_EXISTS, null, ResponseCode.DUPLICATE_USER);
			} else {
				User newUser = new User();
				newUser.setMobileNumber(userModel.getMobileNumber());
				newUser.setName(userModel.getName());
				newUser.setAddress(userModel.getAddress());
				newUser.setPassword(hashPlainPassword(userModel.getPassword()));
				newUser.setCreatedDate(new Date());
				newUser = userRepository.save(newUser);

				logger.info("created user" + newUser);

				UserResponseModel userResponseModel = new UserResponseModel();
				userResponseModel.setMobileNumber(newUser.getMobileNumber());
				userResponseModel.setName(newUser.getName());
				userResponseModel.setAddress(newUser.getAddress());
				

				return commonResponseEntity.ResponseEntityWithMapAndData(ResponseCode.SUCCESS_CODE,
						ConstantVariables.USER_CREATED, newUser, ResponseCode.SUCCESS_CODE);
			}

//		} catch (Exception e) {
//			return commonResponseEntity.ResponseEntityForException();
//		}

	}

	@PostMapping("/validateUser")
	public LinkedHashMap<String, Object> validateUser(@RequestBody UserModel userModel, HttpServletRequest request) {
		logger.info("inside valiadte user controller" + userModel);

		try {

			User user = userRepository.findByMobileNumber(userModel.getMobileNumber());
			logger.info("user" + user);
			if (user != null) {
				Boolean isValidPassword = isValidPasswordProvided(userModel.getPassword(), user.getPassword());
                System.out.println("Final password **************"+hashPlainPassword(userModel.getPassword()));
				if (isValidPassword) {
					UserResponseModel userResponse = new UserResponseModel();
					userResponse.setMobileNumber(user.getMobileNumber());
					userResponse.setName(user.getName());
					userResponse.setAddress(user.getAddress());

					//empty users previous cart
					try {
						Integer deleteCount = userCartRepository.deleteByReferenceUserId(user.getUserId());
					}
					catch(Exception e)
					{
						e.printStackTrace();
						
					}
					List<CategoryGeneric> listOfCategories = categoryGenericRepository
							.findByParentIdAndIsActiveAndIsRoot();

					List<Object> categories = new ArrayList<Object>();

					listOfCategories.stream().forEach(obj -> {

						CategoryModel category = new CategoryModel();
						category.setCategoryId(obj.getCategoryId());
						category.setCategoryName(obj.getCategoryName());
						category.setCategoryType(obj.getCategoryType());
						category.setImageUrl(obj.getImageUrl());
						category.setDescription(obj.getDescription());
						category.setIsRoot(obj.getIsRoot());

						logger.info("hiiiiiiiiiiiiiiii");
						
						List<Object> subCategories = new ArrayList<Object>();
						
				
						obj.getChildren().stream().forEach(subItem -> {
							CategoryModel subCategory = new CategoryModel();
							subCategory.setCategoryId(subItem.getCategoryId());
							subCategory.setCategoryName(subItem.getCategoryName());
							subCategory.setCategoryType(subItem.getCategoryType());
							subCategory.setDescription(subItem.getDescription());
							subCategory.setImageUrl(subItem.getImageUrl());
							subCategory.setIsRoot(subItem.getIsRoot());
							subCategory.setPrice(subItem.getPrice());
							logger.info("hiiiiiiiiiiiiiiii2");
							subCategories.add(subCategory);

						});
						category.setSubCategories(subCategories);

				categories.add(category);

					});

					userResponse.setCategories(categories);

					return commonResponseEntity.ResponseEntityWithMapAndData(ResponseCode.SUCCESS_CODE,
							ConstantVariables.USER_LOGIN_SUCCESS, userResponse, ResponseCode.SUCCESS_CODE);
				} else {
					return commonResponseEntity.ResponseEntityWithMapAndData(ResponseCode.INVALID_PASSWORD,
							ConstantVariables.INVALID_PASSSWORD, null, ResponseCode.INVALID_PASSWORD);
				}

			} else {
				return commonResponseEntity.ResponseEntityWithMapAndData(ResponseCode.NO_DATA_FOUND,
						ConstantVariables.USER_NOT_FOUND, null, ResponseCode.NO_DATA_FOUND);
			}
		} catch (Exception e) {
			return commonResponseEntity.ResponseEntityForException();
		}

	}

	@PostMapping("/addToCart")
	public LinkedHashMap<String, Object> addToCart(@RequestBody UserModel userModel, HttpServletRequest request) {
		logger.info("inside add to cart controller" + userModel);

		try {

			User user = userRepository.findByMobileNumber(userModel.getMobileNumber());
			logger.info("user" + user);

			Optional<CategoryGeneric> category = categoryGenericRepository.findById(userModel.getCategoryId());
			logger.info("category" + category.get());

			if (category.get() == null) {
				return commonResponseEntity.ResponseEntityWithMapAndData(ResponseCode.NO_DATA_FOUND,
						ConstantVariables.ITEM_NOT_FOUND, null, ResponseCode.NO_DATA_FOUND);
			}

			if (user != null) {

				UserCart oldCart = userCartRepository.findByReferenceUserIdAndReferenceCategoryId(user.getUserId(),
						userModel.getCategoryId());

				if (oldCart == null) {

					UserCart userCart = new UserCart();
					userCart.setCategoryDetails(category.get());
					userCart.setUserDetails(user);
					userCart.setQuantity(userModel.getQuantity());
					userCart = userCartRepository.save(userCart);

				} else {

					UserCart userCart = new UserCart();
					userCart.setCartId(oldCart.getCartId());
					userCart.setCategoryDetails(category.get());
					userCart.setQuantity(userModel.getQuantity());
					userCart.setUserDetails(user);
					userCart = userCartRepository.save(userCart);

					userCartRepository.refresh(userCart);

				}

				List<UserCart> cart = userCartRepository.findByReferenceUserId(user.getUserId());

				return commonResponseEntity.ResponseEntityWithMapAndData(ResponseCode.SUCCESS_CODE,
						ConstantVariables.ADD_TO_CART_SUCCESS, cart, ResponseCode.SUCCESS_CODE);
			} else {
				return commonResponseEntity.ResponseEntityWithMapAndData(ResponseCode.NO_DATA_FOUND,
						ConstantVariables.USER_NOT_FOUND, null, ResponseCode.NO_DATA_FOUND);
			}

		}

		catch (Exception e) {
			return commonResponseEntity.ResponseEntityForException();
		}

	}
	
	
	@PostMapping("/deleteFromCart")
	public LinkedHashMap<String, Object> deleteFromCart(@RequestBody UserModel userModel, HttpServletRequest request) {
		logger.info("inside deleteFromCart controller" + userModel);

		try {

			User user = userRepository.findByMobileNumber(userModel.getMobileNumber());
			logger.info("user" + user);

			Optional<CategoryGeneric> category = categoryGenericRepository.findById(userModel.getCategoryId());
			logger.info("category" + category.get());

			if (category.get() == null) {
				return commonResponseEntity.ResponseEntityWithMapAndData(ResponseCode.NO_DATA_FOUND,
						ConstantVariables.ITEM_NOT_FOUND, null, ResponseCode.NO_DATA_FOUND);
			}

			if (user != null) {

				UserCart oldCart = userCartRepository.findByReferenceUserIdAndReferenceCategoryId(user.getUserId(),
						userModel.getCategoryId());

				if (oldCart == null) {

					

				} else {

					Integer deleteCount = userCartRepository.deleteByReferenceUserIdAndReferenceCategoryId(user.getUserId(), category.get().getCategoryId());

					

				}

				List<UserCart> cart = userCartRepository.findByReferenceUserId(user.getUserId());

				return commonResponseEntity.ResponseEntityWithMapAndData(ResponseCode.SUCCESS_CODE,
						ConstantVariables.ADD_TO_CART_SUCCESS, cart, ResponseCode.SUCCESS_CODE);
			} else {
				return commonResponseEntity.ResponseEntityWithMapAndData(ResponseCode.NO_DATA_FOUND,
						ConstantVariables.USER_NOT_FOUND, null, ResponseCode.NO_DATA_FOUND);
			}

		}

		catch (Exception e) {
			return commonResponseEntity.ResponseEntityForException();
		}

	}


	@PostMapping("/checkout")
	public LinkedHashMap<String, Object> checkOut(@RequestBody UserModel userModel, HttpServletRequest request) {
		logger.info("inside checkout controller" + userModel);

		try {

			User user = userRepository.findByMobileNumber(userModel.getMobileNumber());
			logger.info("user" + user);

			if (user != null) {

				List<UserCart> cart = userCartRepository.findByReferenceUserId(user.getUserId());

				Long totalAmount = 0l;

				for (UserCart item : cart) {
					totalAmount = totalAmount + (item.getCategoryDetails().getPrice() * item.getQuantity());

				}

				Integer deleteCount = userCartRepository.deleteByReferenceUserId(user.getUserId());
				UserResponseModel userResponse = new UserResponseModel();
				userResponse.setAddress(user.getAddress());
				userResponse.setMobileNumber(user.getMobileNumber());
				userResponse.setName(user.getName());
				LocalDate today = LocalDate.now();
				LocalDate deliveryDate = today.plusDays(5);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
				String formattedString = deliveryDate.format(formatter);
				userResponse.setDeliveryDate(formattedString);
				userResponse.setOrderId(PasswordUtils.fetchOrderId(user.getMobileNumber()));
				userResponse.setTotalAmount(totalAmount);
// userResponse.setCartDetails(cart);
				return commonResponseEntity.ResponseEntityWithMapAndData(ResponseCode.SUCCESS_CODE,
						ConstantVariables.PAYMENT_SUCCESS, userResponse, ResponseCode.SUCCESS_CODE);
			} else {
				return commonResponseEntity.ResponseEntityWithMapAndData(ResponseCode.NO_DATA_FOUND,
						ConstantVariables.USER_NOT_FOUND, null, ResponseCode.NO_DATA_FOUND);
			}

		}

		catch (Exception e) {
			return commonResponseEntity.ResponseEntityForException();
		}

	}

	private String hashPlainPassword(String plainPassword) {
		return PasswordUtils.generateSecurePassword(plainPassword, ApplicationConstant.SALT);
	}

	private Boolean isValidPasswordProvided(String providedPassword, String securePassword) {                
        return PasswordUtils.verifyUserPassword(providedPassword, securePassword, ApplicationConstant.SALT);
	}

	
}
