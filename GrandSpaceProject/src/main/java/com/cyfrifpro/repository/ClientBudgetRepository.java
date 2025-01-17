package com.cyfrifpro.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cyfrifpro.model.ClientBudget;

public interface ClientBudgetRepository extends JpaRepository<ClientBudget, Long> {
	ClientBudget findByProjectId(Long projectId);
	
//	 Optional<ClientBudget> findByProjectId(Long projectId);
	boolean existsByProjectBudgetId(Long projectBudgetId);

}
