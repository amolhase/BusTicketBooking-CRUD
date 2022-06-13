package com.BusTicketBooking.BusTicketBooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.BusTicketBooking.BusTicketBooking.beans.BookingWrapper;
import com.BusTicketBooking.BusTicketBooking.exception.RecordNotFoundException;
import com.BusTicketBooking.BusTicketBooking.service.BookingService;

@Controller
public class BookingController {

	
	@Autowired
	private BookingService service;
	
	@PostMapping("/saveBooking")
	public ResponseEntity<BookingWrapper> saveBooking(@RequestBody BookingWrapper bookingWrapper) throws RecordNotFoundException {
		bookingWrapper = service.saveBooking(bookingWrapper);
		return ResponseEntity.ok(bookingWrapper);
	}
	
	@GetMapping("/getBooking/{id}")
	public ResponseEntity<BookingWrapper> getBookingById(@PathVariable int id) throws RecordNotFoundException {
		BookingWrapper bookingWrapper = service.getBookingById(id);
		return ResponseEntity.ok(bookingWrapper);
	}
	
	@PutMapping("updateBooking")
	public ResponseEntity<BookingWrapper> updateBookingDetails(@RequestBody BookingWrapper bookingWrapper) throws RecordNotFoundException {
		bookingWrapper = service.updateBookingDetails(bookingWrapper);
		return ResponseEntity.ok(bookingWrapper);
	}
	
	@DeleteMapping("/deleteBooking/{id}")
	public ResponseEntity<Void> deleteBookingById(@PathVariable int id) throws RecordNotFoundException {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
