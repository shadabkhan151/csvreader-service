package com.demo.customException;

import com.demo.model.response.ServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@ControllerAdvice(basePackages = "com.csv")
public class ControllerExceptionHandler {
	/** variable to hold logger object */
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ServiceResponse> handleNullRequest(NullPointerException e){
		ServiceResponse error = new ServiceResponse();
		error.setTimestamp(new Date());
		error.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		error.setStatusMsg(HttpStatus.BAD_REQUEST);
		error.setErrorMsg(e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ServiceResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException e){
		ServiceResponse error = new ServiceResponse();
		error.setTimestamp(new Date());
		error.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		error.setStatusMsg(HttpStatus.BAD_REQUEST);
		error.setErrorMsg(e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ServiceResponse> handleBadRequest(IllegalArgumentException e){
		ServiceResponse error = new ServiceResponse();
		error.setTimestamp(new Date());
		error.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		error.setStatusMsg(HttpStatus.BAD_REQUEST);
		error.setErrorMsg(e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ServiceResponse> handleConstraintViolation(Exception ex, WebRequest request) {
		ServiceResponse error = new ServiceResponse();

		error.setTimestamp(new Date());
		error.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		error.setStatusMsg(HttpStatus.BAD_REQUEST);
		error.setErrorMsg(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);


	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(ServiceFailureException.class)
	public ResponseEntity<ServiceResponse> handleServicefailure(ServiceFailureException e) {
		
		ServiceResponse error = new ServiceResponse();
		error.setTimestamp(new Date());
		error.setStatusCode(HttpServletResponse.SC_BAD_GATEWAY);
		error.setStatusMsg(HttpStatus.INTERNAL_SERVER_ERROR);
		error.setErrorMsg(e.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@SuppressWarnings("rawtypes")
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ServiceResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
		ServiceResponse error = new ServiceResponse();
		error.setTimestamp(new Date());
		error.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		error.setStatusMsg(HttpStatus.BAD_REQUEST);
		
		List<String> errMsgList = new ArrayList<>();
	    ex.getBindingResult().getAllErrors().forEach(errorObj -> {
		errMsgList.add(errorObj.getDefaultMessage());
	    });
	    
	    error.setErrorMsg(errMsgList.toString());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	

	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ServiceResponse> handleExceptionError(Exception e){
		LOGGER.error("ControllerExceptionHandler.handleExceptionError(): Exception occured {}",e);
		ServiceResponse error = new ServiceResponse();
		error.setTimestamp(new Date());
		error.setStatusCode(HttpServletResponse.SC_BAD_GATEWAY);
		error.setStatusMsg(HttpStatus.INTERNAL_SERVER_ERROR);
		error.setErrorMsg(e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<ServiceResponse> handleNoDataFound(NoDataFoundException e) {

		ServiceResponse error = new ServiceResponse();
		error.setTimestamp(new Date());
		error.setStatusCode(HttpServletResponse.SC_OK);
		error.setStatusMsg(HttpStatus.NO_CONTENT);
		error.setErrorMsg(e.getMessage());

		return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
	}

}
