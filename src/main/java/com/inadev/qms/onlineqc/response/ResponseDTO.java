package com.inadev.qms.onlineqc.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> 
{
	private static final String SUCCESS = "SUCCESS";
	private static final String FAILURE = "FAILURE";

	private T data;
	private Integer code;
	private String status;
	private String message;
	public ResponseDTO(T data, Integer code, String status, String message) {
		super();
		this.data = data;
		this.code = code;
		this.status = status;
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
}
