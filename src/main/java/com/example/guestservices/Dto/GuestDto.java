package com.example.guestservices.Dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.example.guestservices.Entity.Guest;

public class GuestDto {

	private Long id;
	
	@NotBlank( message = "first Name is mandatory")
	private String firstName;

	@NotBlank( message = "last Name is mandatory")
	private String lastName;
	
	@NotBlank
	@Email
	private String emailAddress;

	private String address;

	private String country;

	private String state;

	private String phoneNumber;
	
	protected GuestDto() {}
	
	public GuestDto(Long id, String firstName, String lastName, String emailAddress,
			String address, String country, String state, String phoneNumber) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.address = address;
		this.country = country;
		this.state = state;
		this.phoneNumber = phoneNumber;
	}
	
	
	public Guest translateGuestDtoToEntity() {
		Guest guest = new Guest();
		guest.setFirstName(this.firstName);
		guest.setLastName(this.lastName);
		guest.setEmailAddress(this.emailAddress);
		guest.setAddress(this.address);
		guest.setCountry(this.country);
		guest.setState(this.state);
		guest.setPhoneNumber(this.phoneNumber);
		
		return guest;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	
	
}
