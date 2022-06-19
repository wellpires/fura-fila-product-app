package br.com.furafila.productapp.controller;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.furafila.productapp.exception.DimensionNotFoundException;
import br.com.furafila.productapp.exception.ProductNotFoundException;
import br.com.furafila.productapp.response.ErrorResponse;

@RestControllerAdvice
public class ProductControllerAdvice {

	private static final Logger logger = LoggerFactory.getLogger(ProductControllerAdvice.class);

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Void> handleException(Exception ex) {

		logger.error(ex.getMessage(), ex);
		return ResponseEntity.internalServerError().build();

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException maEx) {

		String rejectedValue = maEx.getBindingResult().getFieldErrors().stream().filter(Objects::nonNull).findFirst()
				.map(item -> String.valueOf(item.getRejectedValue())).orElseGet(() -> StringUtils.EMPTY);
		String defaultMessage = maEx.getBindingResult().getFieldError().getDefaultMessage();
		logger.error("{} - Value: {}", defaultMessage, rejectedValue);

		return ResponseEntity.badRequest().body(new ErrorResponse(defaultMessage));
	}

	@ExceptionHandler(DimensionNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Void> handleDimensionNotFoundException(DimensionNotFoundException dnofEx) {
		logger.error(dnofEx.getMessage(), dnofEx);
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Void> handleProductNotFoundException(ProductNotFoundException pnfEx) {
		logger.error(pnfEx.getMessage(), pnfEx);
		return ResponseEntity.notFound().build();
	}

}
