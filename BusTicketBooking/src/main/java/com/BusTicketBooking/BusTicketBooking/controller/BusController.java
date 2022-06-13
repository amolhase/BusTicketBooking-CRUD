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

import com.BusTicketBooking.BusTicketBooking.beans.BusWrapper;
import com.BusTicketBooking.BusTicketBooking.exception.RecordNotFoundException;
import com.BusTicketBooking.BusTicketBooking.service.BusService;

@Controller
public class BusController {
	
	@Autowired
	private BusService busService;

	@PostMapping("/saveBus")
	public ResponseEntity<BusWrapper> saveBus(@RequestBody BusWrapper busWrapper) {
		busWrapper = busService.saveBus(busWrapper);
		return ResponseEntity.ok(busWrapper);
	}
	
	@GetMapping("/getBus/{id}")
	public ResponseEntity<BusWrapper> getBusById(@PathVariable int id)  {
		BusWrapper busWrapper = busService.getBusById(id);
		return ResponseEntity.ok(busWrapper);
	}
	
	@PutMapping("/updateBus")
	public ResponseEntity<BusWrapper> updateBusById(@RequestBody BusWrapper busWrapper) throws RecordNotFoundException {
		busWrapper = busService.updateBusDetails(busWrapper);
		return ResponseEntity.ok(busWrapper);
	}
	
	@DeleteMapping("/deleteBus/{id}")
	public ResponseEntity<Void> deleteBusById(@PathVariable int id) throws RecordNotFoundException {
		busService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
