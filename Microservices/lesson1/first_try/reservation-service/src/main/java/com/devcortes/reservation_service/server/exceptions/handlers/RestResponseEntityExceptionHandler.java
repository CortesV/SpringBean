package com.devcortes.reservation_service.server.exceptions.handlers;

import com.devcortes.reservation_service.server.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

	private final Logger LOG = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResponseEntity handleException(HttpServletRequest httpServletRequest, Exception exception) {
		ErrorResponse response = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), exception
				.getClass().getName(), httpServletRequest.getServletPath(), LocalDateTime.now());
		LOG.error(exception.getMessage());
		return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
