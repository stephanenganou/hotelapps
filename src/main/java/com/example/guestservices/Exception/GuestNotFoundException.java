package com.example.guestservices.Exception;

public class GuestNotFoundException extends RuntimeException {
	public GuestNotFoundException(String msg) {
		super(msg);
	}
}
