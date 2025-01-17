package com.cyfrifpro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cyfrifpro.model.Client;
import com.cyfrifpro.model.Project;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	Optional<Client> findByEmail(String email);
	
	// Custom query to fetch unsubscribed clients
    @Query("SELECT c FROM Client c WHERE c.premium = false")
    List<Client> findUnpremiumedClients();
    
    // Fetch all subscribed clients
    List<Client> findByPremiumTrue();

	Optional<Project> save(Project project);

	boolean existsByEmail(String email);
}
