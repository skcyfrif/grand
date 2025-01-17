package com.cyfrifpro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyfrifpro.model.Client;
import com.cyfrifpro.request.ClientRequest;
import com.cyfrifpro.service.ClientService;
import com.cyfrifpro.util.SecurityUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/client")
@Tag(name="ClientController", description = "By using this class we can map all kind of client requests.")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@GetMapping("/user")
	public String wish() {
		return "hello";
	}
	
//	@PostMapping("/register")
//	@Operation(summary = "Post Api", description = "This is a method for client registration")
//    public ResponseEntity<Client> register(@RequestBody ClientRequest clientRequest) {
//        Client registeredUser = clientService.register(clientRequest);
//        return new ResponseEntity<>(registeredUser, HttpStatus.OK);
//    }
	
//	@PostMapping("/registerAll")
//	@Operation(summary = "Post Api", description = "This is a method for multiple client registration")
//	public ResponseEntity<List<Client>> registerAll(@RequestBody List<ClientRequest> clientRequests) {
//	    List<Client> savedClients = clientService.registerAll(clientRequests);
//	    return new ResponseEntity<>(savedClients, HttpStatus.OK);
//	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Get Api", description = "This is a method for finding client by there id")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Get Api", description = "This is a method for finding client by there email")
    public ResponseEntity<Client> getClientByEmail(@PathVariable String email) {
        return clientService.getClientByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/findAll")
    @Operation(summary = "Get Api", description = "This is a method for finding all clients")
    public ResponseEntity<List<Client>> findAllClients(){
    	List<Client> clients = clientService.findAlClients();
    	return ResponseEntity.ok(clients);
    }
    
    @GetMapping("/unpremium")
    @Operation(summary = "Get Api", description = "This is a method for finding clients who dont purches premium plan")
    public ResponseEntity<List<Client>> getUnsubscribedClients() {
        List<Client> unsubscribedClients = clientService.getUnPremiumedClients();
        return ResponseEntity.ok(unsubscribedClients);
    }

    @GetMapping("/premium")
    @Operation(summary = "Get Api", description = "This is a method for finding clients who purches premium plan")
    public ResponseEntity<List<Client>> getSubscribedClients() {
        List<Client> subscribedClients = clientService.getAllPremiumClients();
        return ResponseEntity.ok(subscribedClients);
    }
    
    @PutMapping("/updatePremium")
    @Operation(summary = "Put Api", description = "This is a method for updating a client's unpremium plan to premium plan")
    public ResponseEntity<Client> updatePremium() {
        try {
            // Get the logged-in user ID using SecurityUtils (This will be the client's ID)
            Long loggedInUserId = SecurityUtils.getLoggedInUserId();

            // Call the service to update the premium status for the logged-in client
            Optional<Client> updatedClient = clientService.updatePremiumStatus(loggedInUserId);

            // Return the response based on whether the client was updated successfully or not
            return updatedClient.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            // Handle any exceptions (optional)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
	
}
