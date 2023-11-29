package com.bcafinance.ewpe.handler;

import com.bcafinance.ewpe.configuration.OtherConfiguration;
import com.bcafinance.ewpe.util.LoggingFile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	private List<ApiValidationError> lsSubError = new ArrayList<ApiValidationError>();

	private String [] strExceptionArr = new String[2];

	public GlobalExceptionHandler() {
		strExceptionArr[0] = "GlobalExceptionHandler";
	}

	@Override
	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
																  HttpHeaders headers,
																  HttpStatus status,
																  WebRequest request) {
		lsSubError.clear();
		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			lsSubError.add(new ApiValidationError(fieldError.getField(),fieldError.getDefaultMessage(),fieldError.getRejectedValue(),fieldError.getObjectName()));
		}

		ApiError apiError =
				new ApiError(HttpStatus.BAD_REQUEST, "TIDAK DAPAT DIPROSES",ex,request.getDescription(false),"X-0001");
//				new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, "TIDAK DAPAT DIPROSES",ex,request.getDescription(false),"X-0001");
		apiError.setSubErrors(lsSubError);
//		return ResponseEntity.unprocessableEntity().body(apiError);
		return new ResponseEntity<Object>(apiError,HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return ResponseEntity.status(apiError.getStatus()).body(apiError);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> dataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {

		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,"DATA TIDAK VALID",ex,request.getDescription(false),"X7006"));
	}

	/*
		simulasikan tabel di delete lalu upload file csv untuk produce error exception UnexpectedRollbackException..
	 */
	@ExceptionHandler(UnexpectedRollbackException.class)
	public ResponseEntity<Object> unexpectedRollbackException(UnexpectedRollbackException ex, WebRequest request) {
		strExceptionArr[1] = "unexpectedRollbackException(UnexpectedRollbackException ex, WebRequest request) \n"+RequestCapture.allRequest(request,null);
		LoggingFile.exceptionStringz(strExceptionArr, ex, OtherConfiguration.getFlagLoging());
		return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,"FLOW GAGAL DI PROSES",ex,request.getDescription(false),"X2120"));
	}
//	@ExceptionHandler(UnexpectedRollbackException.class)
//	public ResponseEntity<Object> unexpectedRollbackException(UnexpectedRollbackException ex, WebRequest request) {
//		return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,"DATA TIDAK DAPAT DIPROSES",ex,request.getDescription(false),"X317"));
//	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,"TIDAK DAPAT DIPROSES",ex,request.getDescription(false),"X2236"));
	}
}