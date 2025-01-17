package com.cyfrifpro.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyfrifpro.model.Manager;
import com.cyfrifpro.model.ManagerBudget;
import com.cyfrifpro.model.Project;
import com.cyfrifpro.repository.ManagerBudgetRepository;
import com.cyfrifpro.repository.ManagerRepository;
import com.cyfrifpro.repository.ProjectRepository;

@Service
public class ManagerBudgetService {

	@Autowired
	private ManagerBudgetRepository managerBudgetRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ManagerRepository managerRepository;

	// Method for managers to submit their budget estimate
	public ManagerBudget submitBudget(Long projectId, Long managerId, BigDecimal estimatedBudget) {
	    // Retrieve the project and manager
	    Project project = projectRepository.findById(projectId)
	            .orElseThrow(() -> new RuntimeException("Project not found"));

	    Manager manager = managerRepository.findById(managerId)
	            .orElseThrow(() -> new RuntimeException("Manager not found"));

	    // Check if the manager has already submitted a budget for the project
	    Optional<ManagerBudget> existingBudget = managerBudgetRepository.findByProjectAndManager(project, manager);
	    if (existingBudget.isPresent()) {
	        throw new RuntimeException("Manager has already submitted a budget for this project");
	    }

	    // Create and save the manager's budget estimate
	    ManagerBudget managerBudget = new ManagerBudget();
	    managerBudget.setProject(project);
	    managerBudget.setManager(manager);
	    managerBudget.setEstimatedBudget(estimatedBudget);

	    // Save the manager budget estimate to the database
	    managerBudget = managerBudgetRepository.save(managerBudget);

	    // Set the project upload date to the current date
	    managerBudget.setUploadDate(LocalDate.now());

	    // Save the updated project
	    projectRepository.save(project);

	    return managerBudget;
	}


	// Method for admin to fetch all budget estimates for a project
//	public List<ManagerBudget> getBudgetsByProject(Long projectId) {
//		return managerBudgetRepository.findByProjectId(projectId);
//	}

	// Method to fetch the estimated budget for a specific project and manager
	public BigDecimal getEstimatedBudget(Long projectId, Long managerId) {
		// Fetch the ManagerBudget using both projectId and managerId
		Optional<ManagerBudget> managerBudget = managerBudgetRepository.findByProjectIdAndManagerId(projectId,
				managerId);

		// If the ManagerBudget is present, return the estimated budget, otherwise
		// return null or throw exception
		return managerBudget.map(ManagerBudget::getEstimatedBudget).orElseThrow(() -> new RuntimeException(
				"Budget not found for Manager ID: " + managerId + " and Project ID: " + projectId));
	}

	// Method to get the count of ManagerBudget estimates
	public long getTotalEstimates() {
		return managerBudgetRepository.count();
	}

	// New Method to fetch all budget estimates across all projects
	public List<ManagerBudget> getAllBudgets() {
		// Retrieve and return all ManagerBudget entries from the repository
		return managerBudgetRepository.findAll();
	}

}
