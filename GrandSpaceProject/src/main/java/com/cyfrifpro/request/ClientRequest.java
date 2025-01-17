package com.cyfrifpro.request;

import lombok.Data;

@Data
public class ClientRequest {

	private Long id;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private String password;
	private String state;
	private String city;
	private String role; // "CLIENT" or "MANAGER"
	private boolean premium;
	private boolean referal;
	private String currentAddress;

}
