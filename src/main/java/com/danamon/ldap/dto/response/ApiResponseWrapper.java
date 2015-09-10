package com.danamon.ldap.dto.response;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * All response from the REST APIs should be wrapped within this particular class.
 *
 * @author yauritux@gmail.com
 *  
 */
public class ApiResponseWrapper<T> extends ResourceSupport {

	private static final String apiVersion = "1.0.0";
	
	private T data;
	private String message;
	
	public ApiResponseWrapper() {}
	
	public ApiResponseWrapper(T data) {
		this.data = data;
	}
	
	public ApiResponseWrapper(String message) {
		this.message = message;
	}
	
	public ApiResponseWrapper(T data, String message) {
		this.data = data;
		this.message = message;
	}
	
	@JsonProperty("api_version")
	public final String getApiVersion() {
		return apiVersion;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}	
}