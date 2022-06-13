package com.BusTicketBooking.BusTicketBooking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.BusTicketBooking.BusTicketBooking.model.Bus;

@Repository
public interface BusRepository extends CrudRepository<Bus, Integer> {

}
