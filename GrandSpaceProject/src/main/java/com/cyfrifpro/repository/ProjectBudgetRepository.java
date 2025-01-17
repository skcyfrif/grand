package com.cyfrifpro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cyfrifpro.model.ProjectBudget;

@Repository
public interface ProjectBudgetRepository extends JpaRepository<ProjectBudget, Long> {
	List<ProjectBudget> findByProjectId(Long projectId);

	ProjectBudget findByProjectIdAndManagerBudgetId(Long projectId, Long managerBudgetId);

	List<ProjectBudget> findByProject_ClientId(Long clientId); // Method to fetch ProjectBudgets by clientId

	// Custom query method to find ProjectBudgets by clientId and publish status
	List<ProjectBudget> findByProject_ClientIdAndPublish(Long clientId, boolean publish);

}