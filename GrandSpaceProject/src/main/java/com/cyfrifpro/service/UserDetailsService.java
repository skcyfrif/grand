package com.cyfrifpro.service;

import com.cyfrifpro.model.Manager;
import com.cyfrifpro.model.Admin;
import com.cyfrifpro.model.Client;
import com.cyfrifpro.repository.ManagerRepository;
import com.cyfrifpro.request.UserDTO;

import com.cyfrifpro.repository.AdminRepository;
import com.cyfrifpro.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cyfrifpro.config.UserInfoConfig;

import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private AdminRepository adminRepository;
	
    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Try to load the user as a Manager first
        Optional<Manager> manager = managerRepository.findByEmail(username);
        if (manager.isPresent()) {
            return new UserInfoConfig(manager.get()); // Use UserInfoConfig for Manager
        }

        // Try to load the user as a Client if the Manager is not found
        Optional<Client> client = clientRepository.findByEmail(username);
        if (client.isPresent()) {
            return new UserInfoConfig(client.get()); // Use UserInfoConfig for Client
        }
        
     // Try to load the user as a Admin if the Client and the Manager is not found
        Optional<Admin> admin = adminRepository.findByEmail(username);
        if (admin.isPresent()) {
            return new UserInfoConfig(admin.get()); // Use UserInfoConfig for Client
        }

        // If neither is found, throw exception
        throw new UsernameNotFoundException("User not found with email: " + username);
    }

 // Register Admin method
    public UserDTO registerAdmin(UserDTO userDTO) throws Exception {
        try {
            // Check if an admin already exists in the database
            if (adminRepository.count() > 0) {
                throw new Exception("An admin is already registered. Only one admin is allowed.");
            }

            // Check if the email is already taken
            if (adminRepository.existsByEmail(userDTO.getEmail())) {
                throw new Exception("Email is already taken by another admin.");
            }

            // Create a new Admin entity
            Admin admin = new Admin();
            admin.setFirstName(userDTO.getFirstName());
            admin.setLastName(userDTO.getLastName());
            admin.setPhone(userDTO.getPhone());
            admin.setEmail(userDTO.getEmail());
            admin.setPassword(userDTO.getPassword());
            admin.setRole(userDTO.getRole());  // Assuming role is "ADMIN"

            // Save the admin to the database
            Admin savedAdmin = adminRepository.save(admin);

            // Convert saved admin to UserDTO
            UserDTO registeredUserDTO = new UserDTO();
            registeredUserDTO.setFirstName(savedAdmin.getFirstName());
            registeredUserDTO.setLastName(savedAdmin.getLastName());
            registeredUserDTO.setPhone(savedAdmin.getPhone());
            registeredUserDTO.setEmail(savedAdmin.getEmail());
            registeredUserDTO.setRole(savedAdmin.getRole());
            registeredUserDTO.setId(savedAdmin.getId());

            return registeredUserDTO;
        } catch (Exception e) {
            throw new Exception("An error occurred while registering the admin.", e);
        }
    }

 // Register Manager method
    public UserDTO registerManager(UserDTO userDTO) throws Exception {
        try {
            // Check if the email is already taken by another manager
            if (managerRepository.existsByEmail(userDTO.getEmail())) {
                throw new Exception("Email is already taken by another manager.");
            }

            // Create a new Manager entity
            Manager manager = new Manager();
            manager.setFirstName(userDTO.getFirstName());
            manager.setLastName(userDTO.getLastName());
            manager.setPhone(userDTO.getPhone());
            manager.setState(userDTO.getState());
            manager.setCity(userDTO.getCity());
            manager.setExperience(userDTO.getExperience());
            manager.setQualifications(userDTO.getQualifications());
            manager.setEmail(userDTO.getEmail());
            manager.setPassword(userDTO.getPassword());
            manager.setRole(userDTO.getRole());  // Assuming role is "MANAGER"

            // Save the manager to the database
            Manager savedManager = managerRepository.save(manager);

            // Convert saved manager to UserDTO
            UserDTO registeredUserDTO = new UserDTO();
            registeredUserDTO.setFirstName(savedManager.getFirstName());
            registeredUserDTO.setLastName(savedManager.getLastName());
            registeredUserDTO.setPhone(savedManager.getPhone());
            registeredUserDTO.setState(savedManager.getState());
            registeredUserDTO.setCity(savedManager.getCity());
            registeredUserDTO.setExperience(savedManager.getExperience());
            registeredUserDTO.setQualifications(savedManager.getQualifications());
            registeredUserDTO.setEmail(savedManager.getEmail());
            registeredUserDTO.setRole(savedManager.getRole());
            registeredUserDTO.setId(savedManager.getId());

            return registeredUserDTO;
        } catch (Exception e) {
            throw new Exception("An error occurred while registering the manager.", e);
        }
    }

 // Register Client method
    public UserDTO registerClient(UserDTO userDTO) throws Exception {
        try {
            // Check if the email is already taken by another client
            if (clientRepository.existsByEmail(userDTO.getEmail())) {
                throw new Exception("Email is already taken by another client.");
            }

            // Create a new Client entity
            Client client = new Client();
            client.setFirstName(userDTO.getFirstName());
            client.setLastName(userDTO.getLastName());
            client.setPhone(userDTO.getPhone());
            client.setState(userDTO.getState());
            client.setCity(userDTO.getCity());
            client.setCurrentAddress(userDTO.getCurrentAddress());
            client.setEmail(userDTO.getEmail());
            client.setPassword(userDTO.getPassword());
            client.setRole(userDTO.getRole());  // Assuming role is "CLIENT"

            // Save the client to the database
            Client savedClient = clientRepository.save(client);

            // Convert saved client to UserDTO
            UserDTO registeredUserDTO = new UserDTO();
            registeredUserDTO.setFirstName(savedClient.getFirstName());
            registeredUserDTO.setLastName(savedClient.getLastName());
            registeredUserDTO.setPhone(savedClient.getPhone());
            registeredUserDTO.setState(savedClient.getState());
            registeredUserDTO.setCity(savedClient.getCity());
            registeredUserDTO.setCurrentAddress(savedClient.getCurrentAddress());
            registeredUserDTO.setEmail(savedClient.getEmail());
            registeredUserDTO.setRole(savedClient.getRole());
            registeredUserDTO.setId(savedClient.getId());

            return registeredUserDTO;
        } catch (Exception e) {
            throw new Exception("An error occurred while registering the client.", e);
        }
    }
}
