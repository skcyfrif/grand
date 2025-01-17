package com.cyfrifpro.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

//import java.util.List;

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
public class Client {
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

	@Column(nullable = false, length = 250)
	private String currentAddress;

	@Column(nullable = true, length = 100)
	private String role; // "CLIENT"

	@Column(nullable = true)
	private boolean premium;

	@OneToMany(mappedBy = "client")
	@JsonBackReference // This marks the back side to prevent recursion
	private List<Project> projects;

//    @Column(nullable = true)
//    private boolean referal;

}
