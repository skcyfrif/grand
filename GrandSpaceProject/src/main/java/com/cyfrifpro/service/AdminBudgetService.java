package com.cyfrifpro.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyfrifpro.model.Manager;
import com.cyfrifpro.model.ManagerBudget;
import com.cyfrifpro.model.Project;
import com.cyfrifpro.model.ProjectBudget;
import com.cyfrifpro.repository.ManagerBudgetRepository;
import com.cyfrifpro.repository.ProjectBudgetRepository;
import com.cyfrifpro.repository.ProjectRepository;

@Service
public class AdminBudgetService {

	@Autowired
	private ManagerBudgetRepository managerBudgetRepository;

	@Autowired
	private ProjectBudgetRepository projectBudgetRepository;

	@Autowired
	private ProjectRepository projectRepository;

	public ProjectBudget createProjectBudget(Long projectId, Long managerBudgetId, BigDecimal materialCost,
			BigDecimal profitMargin) {
		// Get the manager's estimated budget
		ManagerBudget managerBudget = managerBudgetRepository.findById(managerBudgetId)
				.orElseThrow(() -> new IllegalArgumentException("Manager not found with ID: " + managerBudgetId));

		// Get the project entity and validate its existence
		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new IllegalArgumentException("Project not found with ID: " + projectId));

		// Update the project's status to "NOT_ASSIGNED"
		project.setStatus("NOT_ASSIGNED");
		projectRepository.save(project);

		// Get the estimated budget from the manager's budget (as BigDecimal)
		BigDecimal estimatedBudget = managerBudget.getEstimatedBudget();

		// Get the manager who provided the budget
		Manager manager = managerBudget.getManager(); // Assuming ManagerBudget has a reference to Manager

		// Create the final project budget with two scenarios, including the manager
		ProjectBudget projectBudget = new ProjectBudget(managerBudget.getProject(), manager, managerBudget,
				estimatedBudget, materialCost, profitMargin);

		// Save the newly created ProjectBudget
		ProjectBudget savedProjectBudget = projectBudgetRepository.save(projectBudget);

		// Update status of the selected ManagerBudget
		managerBudget.setStatus("SELECTED");
		managerBudgetRepository.save(managerBudget);

		// Update status of other ManagerBudgets for the same project as NOT_SELECTED
		List<ManagerBudget> otherManagerBudgets = managerBudgetRepository.findByProjectId(projectId);
		for (ManagerBudget otherBudget : otherManagerBudgets) {
			if (!otherBudget.getId().equals(managerBudgetId)) {
				otherBudget.setStatus("NOT_SELECTED");
				managerBudgetRepository.save(otherBudget);
			}
		}

		return savedProjectBudget;
	}

	public ProjectBudget updatePublishStatus(Long projectBudgetId) {
		// Find the ProjectBudget by its ID
		ProjectBudget projectBudget = projectBudgetRepository.findById(projectBudgetId)
				.orElseThrow(() -> new IllegalArgumentException("ProjectBudget not found with ID: " + projectBudgetId));

		// Automatically set publish status to true
		projectBudget.setPublish(true);

		// Save the updated ProjectBudget
		return projectBudgetRepository.save(projectBudget);
	}

	// Method to retrieve ProjectBudgets by clientId where publish is true
	public List<ProjectBudget> getProjectBudgetsByClientId(Long clientId) {
		return projectBudgetRepository.findByProject_ClientIdAndPublish(clientId, true);
	}

	// Method to retrieve the project budget
	public ProjectBudget getProjectBudget(Long projectId) {
		return projectBudgetRepository.findById(projectId)
				.orElseThrow(() -> new IllegalArgumentException("ProjectBudget not found"));
	}

	// Method to find all project budgets
	public List<ProjectBudget> getAllProjectBudgets() {
		return projectBudgetRepository.findAll(); // Returns a list of all project budgets
	}
}
