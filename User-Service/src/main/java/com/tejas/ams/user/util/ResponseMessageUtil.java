package com.tejas.ams.user.util;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ResponseMessageUtil {
	
	public  Map<String,Object>  getErrorMessage(String message,HttpStatus status){
		Map<String,Object> responseError = new HashMap<>();
		responseError.put("status", status);
		responseError.put("error", message);
		responseError.put("timestamp", LocalDateTime.now());
		return responseError;
	}
	
	public Map<String,Object>  genericSuccess(Object obj){
		Map<String,Object> response = new HashMap<>();
		response.put("data", obj);
		return response;
	}
	
	public Map<String,Object>  genericSuccess(String message,Object obj){
		Map<String,Object> response = new HashMap<>();
		response.put(message, obj);
		return response;
	}
}
