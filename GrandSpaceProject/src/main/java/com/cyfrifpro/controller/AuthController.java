package com.cyfrifpro.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyfrifpro.config.security.JWTUtil;
import com.cyfrifpro.model.Admin;
import com.cyfrifpro.model.Client;
import com.cyfrifpro.model.Manager;
import com.cyfrifpro.repository.AdminRepository;
import com.cyfrifpro.repository.ClientRepository;
import com.cyfrifpro.repository.ManagerRepository;
import com.cyfrifpro.request.LoginCredentials;
import com.cyfrifpro.request.UserDTO;
import com.cyfrifpro.service.UserDetailsService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:5500/")
public class AuthController {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private ManagerRepository managerRepository;

	@Autowired
	private ClientRepository clientRepository;

	// Register new Manager or Client
	// Register new Manager or Client
	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> registerHandler(@Valid @RequestBody UserDTO userDTO) {
	    try {
	        // Create an instance of BCryptPasswordEncoder
	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	        // Encrypt the password before saving it to the database
	        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
	        userDTO.setPassword(encodedPassword);  // Set the encoded password back to the DTO

	        // Register the user based on their role (Admin or Manager or Client)
	        UserDTO registeredUser = null;
	        if (userDTO.getRole().equals("ADMIN")) {
	        	registeredUser = userDetailsService.registerAdmin(userDTO);    // Assuming this method handles saving the admin
	        }else if (userDTO.getRole().equalsIgnoreCase("MANAGER")) {
	            registeredUser = userDetailsService.registerManager(userDTO);  // Assuming this method handles saving the manager
	        } else if (userDTO.getRole().equalsIgnoreCase("CLIENT")) {
	            registeredUser = userDetailsService.registerClient(userDTO);   // Assuming this method handles saving the client
	        } else {
	            throw new Exception("Invalid role specified");
	        }

	        // Generate JWT token for the newly registered user
	        String token = jwtUtil.generateToken(registeredUser.getId(), registeredUser.getEmail(), registeredUser.getRole());

	        // Return the generated token
	        return new ResponseEntity<>(Collections.singletonMap("jwt-token", token), HttpStatus.CREATED);
	    } catch (AccessException e) {
	        return new ResponseEntity<>(Collections.singletonMap("error", e.getMessage()), HttpStatus.BAD_REQUEST);
	    } catch (Exception e) {
	        e.printStackTrace();  // Log the exception to console
	        return new ResponseEntity<>(Collections.singletonMap("error", "An unexpected error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	// Login endpoint for Manager or Client
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> loginHandler(@Valid @RequestBody LoginCredentials credentials) {
		try {
			// Authenticate the user
			UsernamePasswordAuthenticationToken authCredentials = new UsernamePasswordAuthenticationToken(
					credentials.getEmail(), credentials.getPassword());
			Authentication auth = authenticationManager.authenticate(authCredentials);

			// Fetch authenticated user details
			UserDetails userDetails = (UserDetails) auth.getPrincipal();
			String email = userDetails.getUsername();

			// Retrieve the user's role
			String roleName = userDetails.getAuthorities().stream().findFirst()
					.map(authority -> authority.getAuthority().replace("ROLE_", "")).orElse("USER");

			// Retrieve the user from the database based on the role (Admin or Manager or Client)
			Optional<Admin> adminOptional = adminRepository.findByEmail(email);
			if (adminOptional.isPresent()) {
				Admin admin = adminOptional.get();
				return generateTokenResponse(admin.getId(), email, roleName);
			}
			
			Optional<Manager> managerOptional = managerRepository.findByEmail(email);
			if (managerOptional.isPresent()) {
				Manager manager = managerOptional.get();
				return generateTokenResponse(manager.getId(), email, roleName);
			}

			Optional<Client> clientOptional = clientRepository.findByEmail(email);
			if (clientOptional.isPresent()) {
				Client client = clientOptional.get();
				return generateTokenResponse(client.getId(), email, roleName);
			}

			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Collections.singletonMap("error", "User not found"));

		} catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Collections.singletonMap("error", "Invalid email or password"));
		}
	}

	// Helper method to generate token and response
	private ResponseEntity<Map<String, Object>> generateTokenResponse(Long userId, String email, String roleName) {
		// Generate JWT token
		String token = jwtUtil.generateToken(userId, email, roleName);

		// Create response
		Map<String, Object> response = new HashMap<>();
		response.put("jwt-token", token);
		return ResponseEntity.ok(response);
	}
}
