package com.BusTicketBooking.BusTicketBooking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BusTicketBooking.BusTicketBooking.beans.BookingWrapper;
import com.BusTicketBooking.BusTicketBooking.exception.RecordNotFoundException;
import com.BusTicketBooking.BusTicketBooking.model.Booking;
import com.BusTicketBooking.BusTicketBooking.model.Bus;
import com.BusTicketBooking.BusTicketBooking.model.Passanger;
import com.BusTicketBooking.BusTicketBooking.repository.BookingRepository;
import com.BusTicketBooking.BusTicketBooking.repository.BusRepository;
import com.BusTicketBooking.BusTicketBooking.repository.PassangerRepository;

@Service
public class BookingService {

	@Autowired
	private BookingRepository repository;
	
	@Autowired
	private BusRepository busRepository;
	
	@Autowired
	private PassangerRepository passangerRepository;
	
	public BookingWrapper saveBooking(BookingWrapper bookingWrapper) throws RecordNotFoundException {
		Booking booking = new Booking();
		
		Optional<Bus> busOptionalData = busRepository.findById(bookingWrapper.getBusNo());
		Optional<Passanger> passangerOptionalData = passangerRepository.findById(bookingWrapper.getPid());
		
		if(busOptionalData.isEmpty() || passangerOptionalData.isEmpty()) {
			throw new RecordNotFoundException("Bus/Passanger Not Found");
		}
		Bus b = busOptionalData.get();
		int seats = b.getNoOfSeats();
		long booked = repository.countByBusNo(bookingWrapper.getBusNo());
		System.out.println("Booked "+booked);
		System.out.println("Seats "+seats);
		
		if(busOptionalData.isPresent() && passangerOptionalData.isPresent() && booked < seats) {
			booking.setBusNo(bookingWrapper.getBusNo());
			booking.setPid(bookingWrapper.getPid());
			booking.setPrice(bookingWrapper.getPrice());
			booking = repository.save(booking);
			
			//populate the booking info
			bookingWrapper.setBid(booking.getBid());
			bookingWrapper.setBusNo(booking.getBusNo());
			bookingWrapper.setPid(booking.getPid());
			bookingWrapper.setPrice(booking.getPrice());
			return bookingWrapper;
		}
		else {
			throw new RecordNotFoundException("Bus Record/Passanger Record not found");
		}
		
	}
	
	
//	public Booking saveBooking(Integer busNo, Integer pid, Float price) throws RecordNotFoundException {
//		Booking booking = new Booking();
//		
//		Optional<Bus> busOptionalData = busRepository.findById(busNo);
//		Optional<Passanger> passangerOptionalData = passangerRepository.findById(pid);
//		
//		if(busOptionalData.isEmpty() || passangerOptionalData.isEmpty()) {
//			throw new RecordNotFoundException("Bus/Passanger Not Found");
//		}
//		Bus b = busOptionalData.get();
//		int seats = b.getNoOfSeats();
//		long booked = repository.countByBusNo(busNo);
//		System.out.println("Booked "+booked);
//		System.out.println("Seats "+seats);
//		
//		if(busOptionalData.isPresent() && passangerOptionalData.isPresent() && booked < seats) {
//			booking.setBusNo(busNo);
//			booking.setPid(pid);
//			booking.setPrice(price);
//			booking = repository.save(booking);
//			return booking;
//		}
//		else {
//			throw new RecordNotFoundException("Bus Record/Passanger Record not found");
//		}
//		
//	}
	
	public BookingWrapper getBookingById(int id) throws RecordNotFoundException {
		Optional<Booking> bookingOptionalData = repository.findById(id);
		
		if(bookingOptionalData.isPresent()) {
			BookingWrapper bookingWrapper = new BookingWrapper();
			Booking b = bookingOptionalData.get();
			
			bookingWrapper.setBid(b.getBid());
			bookingWrapper.setBusNo(b.getBusNo());
			bookingWrapper.setPid(b.getPid());
			bookingWrapper.setPrice(b.getPrice());
			return bookingWrapper;
		}
		else {
			throw new RecordNotFoundException("Record not found");
		}
	}
	
	public BookingWrapper updateBookingDetails(BookingWrapper inputBooking) throws RecordNotFoundException {
		Optional<Booking> bookingOptionalData = repository.findById(inputBooking.getBid());
		Optional<Bus> busOptionalData = busRepository.findById(inputBooking.getBusNo());
		Optional<Passanger> passangerOptionalData = passangerRepository.findById(inputBooking.getPid());
		
		
		if(bookingOptionalData.isPresent() && busOptionalData.isPresent() && passangerOptionalData.isPresent()) {
			Booking b = bookingOptionalData.get();
			b.setBusNo(inputBooking.getBusNo());
			b.setPid(inputBooking.getPid());
			b.setPrice(inputBooking.getPrice());
			repository.save(b);
			
			//populate the updated information
			inputBooking.setBid(b.getBid());
			inputBooking.setBusNo(b.getBusNo());
			inputBooking.setPid(b.getPid());
			inputBooking.setPrice(b.getPrice());
			return inputBooking;
		} else {
			throw new RecordNotFoundException("Booking not found");
		}
	}
	
	public void deleteById(int id) throws RecordNotFoundException {
		Optional<Booking> bookingOptionalData = repository.findById(id);
		
		if(bookingOptionalData.isPresent()) {
			Booking booking = bookingOptionalData.get();
			repository.delete(booking);
		} else {
			throw new RecordNotFoundException("Booking record not found");
		}
	}
}
