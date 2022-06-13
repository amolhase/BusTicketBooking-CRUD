package com.BusTicketBooking.BusTicketBooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseStatus(code = HttpStatus.NOT_FOUND)
 public class RecordNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	public RecordNotFoundException() {
	}
	
	public RecordNotFoundException(String message) {
		super(message);
	}
}
