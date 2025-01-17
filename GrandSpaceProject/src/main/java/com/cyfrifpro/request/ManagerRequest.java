package com.cyfrifpro.request;

//import java.util.List;

//import com.cyfrifpro.model.Project;
//import com.cyfrifpro.model.User;

import lombok.Data;

@Data
public class ManagerRequest {

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
	private String experience;
	private String qualifications;
	private Long assignedProjectId;
	private Long managerId;
	private Long projectId;

}
