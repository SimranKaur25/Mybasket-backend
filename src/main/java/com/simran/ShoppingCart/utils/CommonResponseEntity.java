package com.simran.ShoppingCart.utils;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
/**
 * @author Gursimran kaur
 *
 */
@Service("commonResponseEntity")
public class CommonResponseEntity {

	private static final Logger logger = LoggerFactory.getLogger(CommonResponseEntity.class);

	public LinkedHashMap<String, Object> ResponseEntity(Map<String, Object> result) {
		LinkedHashMap<String, Object> resultMap = null;
		resultMap = new LinkedHashMap<String, Object>();
		resultMap.put(ConstantVariables.REQUEST_STATUS, result.get(ConstantVariables.REQUEST_STATUS));
		resultMap.put(ConstantVariables.RESPONSE_MSG, result.get(ConstantVariables.RESPONSE_MSG));
		resultMap.put(ConstantVariables.RESPONSE_CODE, result.get(ConstantVariables.RESPONSE_CODE));
		logger.info("Result :::::: " + resultMap);
		logger.info("success");
		return resultMap;
	}

	public LinkedHashMap<String, Object> ResponseEntityWithMap(Object status, Object msg, Object code) {
		LinkedHashMap<String, Object> resultMap = null;
		resultMap = new LinkedHashMap<String, Object>();
		resultMap.put(ConstantVariables.REQUEST_STATUS, status);
		resultMap.put(ConstantVariables.RESPONSE_MSG, msg);
		resultMap.put(ConstantVariables.RESPONSE_CODE, code);
	logger.info("Result :::::: " + resultMap);
		logger.info("success");

		return resultMap;
	}

	public LinkedHashMap<String, Object> ResponseEntityWithMapAndData(Object status, Object msg, Object data,
			Object code) {
		LinkedHashMap<String, Object> resultMap = null;
		resultMap = new LinkedHashMap<String, Object>();
		resultMap.put(ConstantVariables.REQUEST_STATUS, status);
		resultMap.put(ConstantVariables.RESPONSE_MSG, msg);
		resultMap.put(ConstantVariables.RESPONSE_DATA, data);
		resultMap.put(ConstantVariables.RESPONSE_CODE, code);
		logger.info("Result :::::: " + resultMap);
		logger.info("success");
		return resultMap;
	}

	public LinkedHashMap<String, Object> ResponseEntityForException() {
		LinkedHashMap<String, Object> resultMap = new LinkedHashMap<String, Object>();
		resultMap.put(ConstantVariables.REQUEST_STATUS, HttpStatus.INTERNAL_SERVER_ERROR);
		resultMap.put(ConstantVariables.RESPONSE_MSG, ConstantVariables.EXCEPTION_OCCURED);
		resultMap.put(ConstantVariables.RESPONSE_CODE,ResponseCode.EXCEPTION_CODE);
		logger.info("Result :::::: " + resultMap);
		logger.info("success");
		return resultMap;
	}
}
