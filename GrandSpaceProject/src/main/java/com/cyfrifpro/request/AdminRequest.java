package com.cyfrifpro.request;

import lombok.Data;

@Data
public class AdminRequest {

	private Long id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String phone;
	private String role; // ADMIN

}
