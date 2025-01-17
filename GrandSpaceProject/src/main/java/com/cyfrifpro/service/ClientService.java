package com.cyfrifpro.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyfrifpro.model.Client;
import com.cyfrifpro.repository.ClientRepository;
import com.cyfrifpro.request.ClientRequest;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ModelMapper modelMapper;

//	// add a client
//	public Client register(ClientRequest clientRequest) {
//		Client client = modelMapper.map(clientRequest, Client.class);
//		client.setPremium(false);// Set premium to false on registration
//		client.setRole("CLIENT");
//
//		return clientRepository.save(client);
//	}
//
//	// Add multiple clients
//	public List<Client> registerAll(List<ClientRequest> clientRequests) {
//		List<Client> clients = clientRequests.stream().map(clientRequest -> {
//			Client client = modelMapper.map(clientRequest, Client.class);
//			// Set the default role if not already set
//			if (client.getRole() == null || client.getRole().isEmpty()) {
//				client.setRole("CLIENT");
//			}
//			return client;
//		}).collect(Collectors.toList());
//
//		return clientRepository.saveAll(clients);
//	}

	// Fetch a client by ID
	public Optional<Client> getClientById(Long id) {
		return clientRepository.findById(id);
	}

	// Fetch a client by Email
	public Optional<Client> getClientByEmail(String email) {
		return clientRepository.findByEmail(email);
	}

	// Fetch all clients at a time
	public List<Client> findAlClients() {
		return clientRepository.findAll();
	}

	// Fetch list of unsubscribed clients
	public List<Client> getUnPremiumedClients() {
		return clientRepository.findUnpremiumedClients();
	}

	// Fetch all subscribed clients
	public List<Client> getAllPremiumClients() {
		return clientRepository.findByPremiumTrue();
	}

	public Optional<Client> updatePremiumStatus(Long loggedInUserId) {
	    // Find the client using the logged-in user ID
	    Optional<Client> client = clientRepository.findById(loggedInUserId);
	    
	    if (client.isPresent()) {
	        Client clientToUpdate = client.get();
	        
	        // Set the premium status to true
	        clientToUpdate.setPremium(true);
	        
	        // Save the updated client
	        clientRepository.save(clientToUpdate);
	        
	        return Optional.of(clientToUpdate);
	    }
	    
	    // If the client is not found, return Optional.empty()
	    return Optional.empty();
	}

}
