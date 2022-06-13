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

import com.BusTicketBooking.BusTicketBooking.beans.PassangerWrapper;
import com.BusTicketBooking.BusTicketBooking.exception.RecordNotFoundException;
import com.BusTicketBooking.BusTicketBooking.service.PassangerService;

@Controller
public class PassangerController {

	@Autowired
	private PassangerService passangerService;
	
	@PostMapping("/savePassanger")
	public ResponseEntity<PassangerWrapper> savePassanger(@RequestBody PassangerWrapper passangerWrapper) {
		passangerWrapper = passangerService.savePassanger(passangerWrapper);
		return ResponseEntity.ok(passangerWrapper);
	}
	
	@GetMapping("/getPassanger/{id}")
	public ResponseEntity<PassangerWrapper> getPassangerById(@PathVariable int id) throws RecordNotFoundException  {
		PassangerWrapper passangerWrapper = passangerService.getPassangerById(id);
		return ResponseEntity.ok(passangerWrapper);
	}
	
	@PutMapping("/updatePassanger")
	public ResponseEntity<PassangerWrapper> updatePassangerById(@RequestBody PassangerWrapper passangerWrapper) throws RecordNotFoundException {
		passangerWrapper = passangerService.updatePassangerDetails(passangerWrapper);
		return ResponseEntity.ok(passangerWrapper);
	}
	
	@DeleteMapping("/deletePassanger/{id}")
	public ResponseEntity<Void> deletePassangerById(@PathVariable int id) throws RecordNotFoundException {
		passangerService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
