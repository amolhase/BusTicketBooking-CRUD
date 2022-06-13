package com.BusTicketBooking.BusTicketBooking.resolver;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.BusTicketBooking.BusTicketBooking.exception.RecordNotFoundException;
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
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

@Component
public class Mutation implements GraphQLMutationResolver {

	private AuthorRepository authorRepository;
	private TutorialRepository tutorialRepository;
	private BusRepository busRepository;
	private PassangerRepository passangerRepository;
	private BookingRepository bookingRepository;

	@Autowired
	public Mutation(AuthorRepository authorRepository, TutorialRepository tutorialRepository,
			BusRepository busRepository, PassangerRepository passangerRepository, BookingRepository bookingRepository) {
		this.authorRepository = authorRepository;
		this.tutorialRepository = tutorialRepository;
		this.busRepository = busRepository;
		this.passangerRepository = passangerRepository;
		this.bookingRepository = bookingRepository;
	}

	public Author createAuthor(String name, Integer age) {
		Author author = new Author();
		author.setName(name);
		author.setAge(age);
		authorRepository.save(author);
		return author;
	}

	public Bus createBus(String startLoc, String destination, Integer noOfSeats) {
		Bus bus = new Bus();
		bus.setStartLoc(startLoc);
		bus.setDestination(destination);
		bus.setNoOfSeats(noOfSeats);
		busRepository.save(bus);
		return bus;
	}

	public Passanger createPassanger(String name, String address, Integer age) {
		Passanger passanger = new Passanger();
		passanger.setName(name);
		passanger.setAddress(address);
		passanger.setAge(age);
		passangerRepository.save(passanger);
		return passanger;
	}

	public Tutorial createTutorial(String title, String description, Long authorId) {
		Tutorial tutorial = new Tutorial();
		tutorial.setAuthor(new Author(authorId));
		tutorial.setTitle(title);
		tutorial.setDescription(description);
		tutorialRepository.save(tutorial);
		return tutorial;
	}
	
	public Booking createBooking(Integer busNo, Integer pid, double price) {
		Booking booking = new Booking();
		booking.setBusNo(busNo);
		booking.setPid(pid);
		booking.setPrice(price);
		bookingRepository.save(booking);
		return booking;
	}

	public boolean deleteTutorial(Long id) {
		tutorialRepository.deleteById(id);
		return true;
	}

	public boolean deleteBus(Integer id) {
		busRepository.deleteById(id);
		return true;
	}

	public boolean deletePassanger(Integer id) {
		passangerRepository.deleteById(id);
		return true;
	}
	
	public boolean deleteBooking(Integer id) {
		bookingRepository.deleteById(id);
		return true;
	}

	public Tutorial updateTutorial(Long id, String title, String description) throws RecordNotFoundException {
		Optional<Tutorial> optTutorial = tutorialRepository.findById(id);
		if (optTutorial.isPresent()) {
			Tutorial tutorial = optTutorial.get();
			if (title != null)
				tutorial.setTitle(title);
			if (description != null)
				tutorial.setDescription(description);
			tutorialRepository.save(tutorial);
			return tutorial;
		} else {
			throw new RecordNotFoundException("Not found Tutorial to update!");
		}
	}

	public Bus updateBus(Integer id, String startLoc, String destination, Integer noOfSeats)
			throws RecordNotFoundException {
		Optional<Bus> busOptional = busRepository.findById(id);
		if (busOptional.isPresent()) {
			Bus bus = busOptional.get();
			if (startLoc != null)
				bus.setStartLoc(startLoc);
			if (destination != null)
				bus.setDestination(destination);
			if (noOfSeats != null)
				bus.setNoOfSeats(noOfSeats);
			busRepository.save(bus);
			return bus;
		} else {
			throw new RecordNotFoundException("Bus Record Not Found");
		}
	}

	public Passanger updatePassanger(Integer id, String name, String address, Integer age)
			throws RecordNotFoundException {
		Optional<Passanger> passangerOptional = passangerRepository.findById(id);
		if (passangerOptional.isPresent()) {
			Passanger passanger = passangerOptional.get();
			if (name != null)
				passanger.setName(name);
			if (address != null)
				passanger.setAddress(address);
			if (age != null)
				passanger.setAge(age);
			passangerRepository.save(passanger);
			return passanger;
		} else {
			throw new RecordNotFoundException("Passanger Not Found");
		}
	}
	
	public Booking updateBooking(Integer id, Integer busNo, Integer pid, Double price) throws RecordNotFoundException {
		Optional<Booking> bookingOptional = bookingRepository.findById(id);
		if(bookingOptional.isPresent()) {
			Booking booking = bookingOptional.get();
			if(busNo != null)
				booking.setBusNo(busNo);
			if(pid != null)
				booking.setPid(pid);
			if(price != null)
				booking.setPrice(price);
			bookingRepository.save(booking);
			return booking;
		}else {
			throw new RecordNotFoundException("Booking not available");
		}
	}
}
