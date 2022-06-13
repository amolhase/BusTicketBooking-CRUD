package com.BusTicketBooking.BusTicketBooking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int busNo;
	private String startLoc;
	private String destination;
	private int noOfSeats;
	
	public int getBusNo() {
		return busNo;
	}
	public void setBusNo(int busNo) {
		this.busNo = busNo;
	}
	public String getStartLoc() {
		return startLoc;
	}
	public void setStartLoc(String startLoc) {
		this.startLoc = startLoc;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getNoOfSeats() {
		return noOfSeats;
	}
	public void setNoOfSeats(int i) {
		this.noOfSeats = i;
	}
	
	
	
}
