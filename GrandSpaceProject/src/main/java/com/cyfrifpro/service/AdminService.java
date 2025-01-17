package com.cyfrifpro.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyfrifpro.model.Manager;
import com.cyfrifpro.model.Project;
//import com.cyfrifpro.repository.ManagerBudgetRepository;
import com.cyfrifpro.repository.ManagerRepository;
import com.cyfrifpro.repository.ProjectRepository;

@Service
public class AdminService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ManagerRepository managerRepository;
	
//	@Autowired
//	private ManagerBudgetRepository managerBudgetRepository;

	public void assignProjectToManagers(Project project) {
	    // Get all managers
	    List<Manager> managers = managerRepository.findAll();

	    // Assign the project to all managers
	    for (Manager manager : managers) {
	        // Add project to manager's list for bidding or estimates
	        manager.addProjectToBidList(project);

	        // Save the manager's updated information if necessary
	        managerRepository.save(manager);
	    }

	    // Update the project status to "AWAITING_ESTIMATES"
	    project.setStatus("AWAITING_ESTIMATES");

	    // Set the current date to the 'estimatedByVendor' field
	    LocalDate estimatedByVendorDate = LocalDate.now();
	    project.setEstimatedByVendor(estimatedByVendorDate);

	    // Calculate the expiration date (7 days after the estimatedByVendor date)
//	    LocalDate expireDate = estimatedByVendorDate.plusDays(7);

	    // Save the updated project with the estimatedByVendor field
	    projectRepository.save(project);

	    // Save the expireDate for each ManagerBudget (if required)
//	    List<ManagerBudget> managerBudgets = managerBudgetRepository.findByProjectId(project.getId());
//	    for (ManagerBudget managerBudget : managerBudgets) {
//	        managerBudget.setExpireDate(expireDate);
//	        managerBudgetRepository.save(managerBudget);
//	    }
	}

}
