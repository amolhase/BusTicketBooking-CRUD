package com.BusTicketBooking.BusTicketBooking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BusTicketBooking.BusTicketBooking.beans.PassangerWrapper;
import com.BusTicketBooking.BusTicketBooking.exception.RecordNotFoundException;
import com.BusTicketBooking.BusTicketBooking.model.Passanger;
import com.BusTicketBooking.BusTicketBooking.repository.PassangerRepository;

@Service
public class PassangerService {

	@Autowired
	private PassangerRepository repository;
	
	public PassangerWrapper savePassanger(PassangerWrapper inputPassanger) {
		Passanger p = new Passanger();
		p.setName(inputPassanger.getName());
		p.setAddress(inputPassanger.getAddress());
		p.setAge(inputPassanger.getAge());
		p = repository.save(p);
		
		inputPassanger.setPid(p.getPid());
		inputPassanger.setName(p.getName());
		inputPassanger.setAddress(p.getAddress());
		inputPassanger.setAge(p.getAge());
		return inputPassanger;
		
	}
	
	public PassangerWrapper getPassangerById(int id) throws RecordNotFoundException {
		PassangerWrapper passangerWrapperOutput = null;
		
		Optional<Passanger> passangerOptionalData = repository.findById(id);
		
		if(passangerOptionalData.isPresent()) {
			passangerWrapperOutput = new PassangerWrapper();
			Passanger passanger = passangerOptionalData.get();
			passangerWrapperOutput.setPid(passanger.getPid());
			passangerWrapperOutput.setName(passanger.getName());
			passangerWrapperOutput.setAddress(passanger.getAddress());
			passangerWrapperOutput.setAge(passanger.getAge());
			return passangerWrapperOutput;
		}
		 else {
				throw new RecordNotFoundException("Passanger Record Not Found");
			}
	}
	
	public PassangerWrapper updatePassangerDetails(PassangerWrapper inputPassanger) throws RecordNotFoundException{
		Optional<Passanger> passangerOptionalData = repository.findById(inputPassanger.getPid());
		
		if(passangerOptionalData.isPresent()) {
			Passanger passanger = passangerOptionalData.get();
			
			//update the name
			passanger.setName(inputPassanger.getName());
			passanger.setAddress(inputPassanger.getAddress());
			passanger.setAge(inputPassanger.getAge());
			repository.save(passanger);
			
			//populate the updated details in the output objects
			PassangerWrapper passangerWrapper = new PassangerWrapper();
			passangerWrapper.setPid(passanger.getPid());
			passangerWrapper.setName(passanger.getName());
			passangerWrapper.setAddress(passanger.getAddress());
			passangerWrapper.setAge(passanger.getAge());
			return passangerWrapper;
		} else {
			throw new RecordNotFoundException("Passanger Record Not Found");
		}
	}
	
	public void deleteById(int id) throws RecordNotFoundException {
		Optional<Passanger> passangerOptionalData = repository.findById(id);
		
		if(passangerOptionalData.isPresent()) {
			Passanger passanger = passangerOptionalData.get();
			repository.delete(passanger);
		} else {
			throw new RecordNotFoundException("Passanger record not found");
		}
	}
}
