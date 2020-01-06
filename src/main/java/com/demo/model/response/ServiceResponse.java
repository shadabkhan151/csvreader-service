package com.demo.model.response;

import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class ServiceResponse<T> {

	private Date timestamp;
	private int statusCode;
	private HttpStatus statusMsg;
	private String errorCode;
	private String errorMsg;
	private T payload;

	public ServiceResponse(){
		
		this.timestamp = new Date();
		this.statusCode = HttpServletResponse.SC_OK;
		this.statusMsg = HttpStatus.OK;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int scCreated) {
		this.statusCode = scCreated;
	}

	public HttpStatus getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(HttpStatus statusMsg) {
		this.statusMsg = statusMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String created) {
		this.errorMsg = created;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}

}