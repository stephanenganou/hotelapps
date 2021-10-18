package com.example.guestservices.Web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.guestservices.Dto.GuestDto;
import com.example.guestservices.Entity.Guest;
import com.example.guestservices.Exception.GuestNotFoundException;
import com.example.guestservices.Repo.GuestRepository;

@RestController
@RequestMapping(path = "/guests")
public class GuestServicesController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GuestServicesController.class);
	
	private final GuestRepository guestRepository;
	
	@Autowired
	public GuestServicesController(GuestRepository guestRepository) {
		this.guestRepository = guestRepository;
	}
	
	@GetMapping
	public List<GuestDto> getAllGuests(){
		LOGGER.info("Requesting all Guests. Path: '/guests'");
		return this.guestRepository.findAll()
				.stream().map(g -> g.translateEntityToGuestDto())
				.collect(Collectors.toList());
	}
	
	@PostMapping
	public ResponseEntity<GuestDto> addGuest(@Valid @RequestBody GuestDto guestDto){
		LOGGER.info("Posting a Guest. Path: '/guests'");
		GuestDto g_Dto = this.guestRepository.save(guestDto.translateGuestDtoToEntity()).translateEntityToGuestDto();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(g_Dto.getId()).toUri();
		
		return ResponseEntity.created(location).body(g_Dto);
	}
	
	@GetMapping(value = "/{id}")
	public GuestDto getGuest(@PathVariable("id") Long id) {
		LOGGER.info("Requesting Guest id: {}. Path: '/guests/{}'", id, id);
		Optional<Guest> guest = this.guestRepository.findById(id);
		if(guest.isPresent()) {
			return guest.get().translateEntityToGuestDto();
		}else {
			throw new GuestNotFoundException("Guest with id: " +id+ ", not found");
		}
	}
	
	@PutMapping(value = "/{id}")
	public GuestDto updateGuest(@PathVariable("id") Long id, @Valid @RequestBody GuestDto guestDto) {
		Optional<Guest> existingGuest = this.guestRepository.findById(id);
		
		if(existingGuest.isPresent()) {
			Guest g = existingGuest.get();
			g.setFirstName(guestDto.getFirstName());
			g.setLastName(guestDto.getLastName());
			g.setEmailAddress(guestDto.getEmailAddress());
			g.setAddress(guestDto.getAddress());
			g.setCountry(guestDto.getCountry());
			g.setState(guestDto.getState());
			g.setPhoneNumber(guestDto.getPhoneNumber());
			
			return this.guestRepository.save(g).translateEntityToGuestDto();
		}else {
			throw new GuestNotFoundException("Guest with id: " +id+ ", not found");
		}
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.RESET_CONTENT)
	public void deleteGuest(@PathVariable Long id) {
		Optional<Guest> existingGuest = this.guestRepository.findById(id);
		
		if(existingGuest.isPresent()) {
			this.guestRepository.deleteById(id);
		}else {
			throw new GuestNotFoundException("Guest with id: " +id+ ", not found");
		}
	}
}
