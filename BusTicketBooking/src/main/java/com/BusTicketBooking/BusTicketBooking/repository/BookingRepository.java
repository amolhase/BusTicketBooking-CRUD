package com.BusTicketBooking.BusTicketBooking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.BusTicketBooking.BusTicketBooking.model.Booking;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {

	long countByBusNo(int busNo);
}
