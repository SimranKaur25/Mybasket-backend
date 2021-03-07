package com.simran.ShoppingCart.utils;
/**
 * @author Gursimran kaur
 *
 */
public class ResponseCode {

	public static int TOKEN_INVALID = 4002;
	
	public static int SUCCESS_CODE = 2000; // Success
	public static int NO_DATA_FOUND = 2004; // No data found
	public static int DUPLICATE_USER=2003;
	public static int INVALID_PASSWORD=2001;
	
	public static int INPROCESS = 2005;
	public static int PARAM_MISSING_CODE = 4000; // Bad request / parameter missing
	public static int TOKEN_EXPIRE_CODE = 4001; // Token expire/Unauthorized
	public static int INACTIVE_USER_CODE = 4003; // Inactive user
	public static int USSD_REQUEST_FAIL_CODE = 4007; // ussd request fail
	public static int ALREDAY_EXIST_CODE = 4009; // Conflict
	public static int NOT_EXIST_CODE = 4010; // Conflict
	public static int EXCEPTION_CODE = 5000; // The general catch-all error when the server-side throws an exception.
	public static int BED_GATWAY_CODE = 5002; // bed gatway
	public static int SERVICE_UNAVAILABLE_CODE = 5003; // Service Unavailable
	public static int ACTION_NOT_CONFIGURED_CODE = 5004; // Url for this action is not configured
	public static int NETWORK_READ_TIMEOUT_CODE = 5098; // Network read timeout error
	public static int NETWORK_CONNECT_TIMEOUT__CODE = 5099; // Network connect timeout error
	public static int INVALID_FILE = 5007; // Invalid file format
	public static String INVALID_USER_DETAIL = "4010"; // invalid passwoerdl
	public static int HTTP_SUCCESS_CODE = 200;
	
}
