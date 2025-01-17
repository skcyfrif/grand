package com.cyfrifpro.model;

import java.util.ArrayList;
import java.util.List;
//import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Manager {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String firstName;

	@Column(nullable = false, length = 100)
	private String lastName;

	@Column(nullable = false, length = 15)
	private String phone;

	@Column(nullable = false, length = 100, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false, length = 50)
	private String state;

	@Column(nullable = false, length = 50)
	private String city;

	@Column(nullable = true, length = 100)
	private String role; // "CLIENT" or "MANAGER"

	@Column(nullable = true)
	private boolean premium;

	@Column(nullable = false, name = "experience")
	private String experience;

	@Column(nullable = false)
	private String qualifications;

	@OneToMany(mappedBy = "manager")
	@JsonBackReference
	private List<Project> projectsToBid;

	@Column(name = "assigned_project_id", nullable = true)
	private Long assignedProjectId; // NULL if the manager is not assigned to any project

	public void addProjectToBidList(Project project) {
		if (projectsToBid == null) {
			projectsToBid = new ArrayList<>();
		}
		projectsToBid.add(project);
	}

}
