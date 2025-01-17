package com.cyfrifpro.request;

import lombok.Data;

@Data
public class UserDTO {
	private Long id;
	
    private String email;
    private String password;
    private String role; // MANAGER or CLIENT or ADMIN
    
	private String firstName;
	private String lastName;
	private String phone;
	private String state;
	private String city;
	private String currentAddress;
	private boolean premium;

	//Manager's Extra fields.
	private String qualifications;
	private String experience;

}
