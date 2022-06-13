package com.BusTicketBooking.BusTicketBooking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BusTicketBooking.BusTicketBooking.beans.BusWrapper;
import com.BusTicketBooking.BusTicketBooking.exception.RecordNotFoundException;
import com.BusTicketBooking.BusTicketBooking.model.Bus;
import com.BusTicketBooking.BusTicketBooking.repository.BusRepository;


@Service
public class BusService {
	
	@Autowired
	private BusRepository repository;
	
	public BusWrapper saveBus(BusWrapper inputBus) {
		Bus b = new Bus();
		//b.setBusNo(inputBus.getBusNo());
		b.setStartLoc(inputBus.getStartLoc());
		b.setDestination(inputBus.getDestination());
		b.setNoOfSeats(inputBus.getNoOfSeats());
		b = repository.save(b);
		
		inputBus.setBusNo(b.getBusNo());
		inputBus.setStartLoc(b.getStartLoc());
		inputBus.setDestination(b.getDestination());
		inputBus.setNoOfSeats(b.getNoOfSeats());
		return inputBus;
	}
	
	public BusWrapper getBusById(int id) {
		BusWrapper busWrapperOutput = null;
		
		Optional<Bus> busOptionalData = repository.findById(id);
		
		if(busOptionalData.isPresent()) {
			busWrapperOutput = new BusWrapper();
			Bus bus = busOptionalData.get();
			busWrapperOutput.setBusNo(bus.getBusNo());
			busWrapperOutput.setStartLoc(bus.getStartLoc());
			busWrapperOutput.setDestination(bus.getDestination());
			busWrapperOutput.setNoOfSeats(bus.getNoOfSeats());
		}
		return busWrapperOutput;
	}
	
	public BusWrapper updateBusDetails(BusWrapper inputBus) throws RecordNotFoundException{
		Optional<Bus> busOptionalData = repository.findById(inputBus.getBusNo());
		
		if(busOptionalData.isPresent()) {
			Bus bus = busOptionalData.get();
			
			//update the name
			bus.setDestination(inputBus.getDestination());
			bus.setStartLoc(inputBus.getStartLoc());
			bus.setNoOfSeats(inputBus.getNoOfSeats());
			repository.save(bus);
			
			//populate the updated details in the output objects
			BusWrapper busWrapper = new BusWrapper();
			busWrapper.setBusNo(bus.getBusNo());
			busWrapper.setStartLoc(bus.getStartLoc());
			busWrapper.setDestination(bus.getDestination());
			busWrapper.setNoOfSeats(bus.getNoOfSeats());
			return busWrapper;
		} else {
			throw new RecordNotFoundException("Bus Record Not Found");
		}
	}
	
	public void deleteById(int id) throws RecordNotFoundException {
		Optional<Bus> busOptionalData = repository.findById(id);
		
		if(busOptionalData.isPresent()) {
			Bus bus = busOptionalData.get();
			repository.delete(bus);
		} else {
			throw new RecordNotFoundException("Bus record not found");
		}
	}
}
