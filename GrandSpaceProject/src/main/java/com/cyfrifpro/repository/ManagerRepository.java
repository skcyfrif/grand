package com.cyfrifpro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cyfrifpro.model.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {

	Optional<Manager> findByEmail(String email);

	// Custom query to fetch unsubscribed clients
	@Query("SELECT c FROM Manager c WHERE c.premium = false")
	List<Manager> findUnpremiumedManagers();

	// Fetch all subscribed clients
	List<Manager> findByPremiumTrue();

	@Query("SELECT m FROM Manager m WHERE m.assignedProjectId IS NULL")
	Optional<Manager> findAvailableManager();

	@Query("SELECT COUNT(m) FROM Manager m WHERE m.premium = true")
	long countPremiumManagers();

	boolean existsByEmail(String email);

}
