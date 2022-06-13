package com.BusTicketBooking.BusTicketBooking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.BusTicketBooking.BusTicketBooking.model.Passanger;

@Repository
public interface PassangerRepository extends CrudRepository<Passanger, Integer> {

}
