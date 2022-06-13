package com.BusTicketBooking.BusTicketBooking.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.BusTicketBooking.BusTicketBooking.model.Author;
import com.BusTicketBooking.BusTicketBooking.model.Booking;
import com.BusTicketBooking.BusTicketBooking.model.Bus;
import com.BusTicketBooking.BusTicketBooking.model.Passanger;
import com.BusTicketBooking.BusTicketBooking.model.Tutorial;
import com.BusTicketBooking.BusTicketBooking.repository.AuthorRepository;
import com.BusTicketBooking.BusTicketBooking.repository.BookingRepository;
import com.BusTicketBooking.BusTicketBooking.repository.BusRepository;
import com.BusTicketBooking.BusTicketBooking.repository.PassangerRepository;
import com.BusTicketBooking.BusTicketBooking.repository.TutorialRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class Query implements GraphQLQueryResolver {

	private AuthorRepository authorRepository;
	private TutorialRepository tutorialRepository;
	private BusRepository busRepository;
	private PassangerRepository passangerRepository;
	private BookingRepository bookingRepository;

	@Autowired
	public Query(AuthorRepository authorRepository, TutorialRepository tutorialRepository, BusRepository busRepository,
			PassangerRepository passangerRepository, BookingRepository bookingRepository) {
		this.authorRepository = authorRepository;
		this.tutorialRepository = tutorialRepository;
		this.busRepository = busRepository;
		this.passangerRepository = passangerRepository;
		this.bookingRepository = bookingRepository;
	}

	public Iterable<Author> findAllAuthors() {
		return authorRepository.findAll();
	}

	public Iterable<Tutorial> findAllTutorials() {
		return tutorialRepository.findAll();
	}

	public Iterable<Bus> findAllBus() {
		return busRepository.findAll();
	}

	public Iterable<Passanger> findAllPassanger() {
		return passangerRepository.findAll();
	}

	public Iterable<Booking> findAllBooking() {
		return bookingRepository.findAll();
	}

	public long countAuthors() {
		return authorRepository.count();
	}

	public long countTutorials() {
		return tutorialRepository.count();
	}
}
