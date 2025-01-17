package com.cyfrifpro.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyfrifpro.model.BudgetOption;
import com.cyfrifpro.model.ClientBudget;
import com.cyfrifpro.model.ManagerBudget;
import com.cyfrifpro.model.Project;
import com.cyfrifpro.model.ProjectBudget;
import com.cyfrifpro.repository.ClientBudgetRepository;
import com.cyfrifpro.repository.ManagerBudgetRepository;
import com.cyfrifpro.repository.ProjectBudgetRepository;
import com.cyfrifpro.repository.ProjectRepository;

@Service
public class ClientBudgetService {

	@Autowired
	private ClientBudgetRepository clientBudgetRepository;

	@Autowired
	private ManagerBudgetRepository managerBudgetRepository;

	@Autowired
	private ProjectBudgetRepository projectBudgetRepository;

	@Autowired
	private ProjectRepository projectRepository;

	public void confirmBudgetSelection(Long projectBudgetId, BudgetOption selectedOption) {
	    // Fetch the project budget
	    ProjectBudget projectBudget = projectBudgetRepository.findById(projectBudgetId).orElseThrow(
	            () -> new IllegalArgumentException("ProjectBudget not found for project ID: " + projectBudgetId));

	    // Check if a ClientBudget already exists for the given ProjectBudget
	    boolean clientBudgetExists = clientBudgetRepository.existsByProjectBudgetId(projectBudgetId);
	    if (clientBudgetExists) {
	        throw new IllegalStateException("Budget has already been confirmed for this ProjectBudget ID: " + projectBudgetId);
	    }

	    // Fetch the project associated with the project budget
	    Project project = projectBudget.getProject();

	    // Fetch the manager budget for the project
	    ManagerBudget managerBudget2 = projectBudget.getManagerBudget();
	    ManagerBudget managerBudget = managerBudgetRepository.findById(managerBudget2.getId())
	            .orElseThrow(() -> new IllegalArgumentException(
	                    "ManagerBudget not found for project ID: " + managerBudget2.getId()));

	    // Calculate the final budget based on the selected option
	    BigDecimal finalBudget = selectedOption.isWithMaterials() ? projectBudget.getBudgetWithMaterials()
	            : projectBudget.getBudgetWithoutMaterials();

	    // Initialize the ClientBudget entity
	    ClientBudget clientBudget = new ClientBudget();
	    clientBudget.setProject(project);
	    clientBudget.setWithMaterials(selectedOption.isWithMaterials());
	    clientBudget.setFinalBudget(finalBudget);

	    // Populate additional fields
	    clientBudget.setProjectName(project.getName()); // From Project entity
	    clientBudget.setClientProjectBudget(project.getBudget()); // From Project entity
	    clientBudget.setManagerEstimate(managerBudget.getEstimatedBudget()); // From ManagerBudget entity

	    // Set the material budget
	    if (selectedOption.isWithMaterials()) {
	        clientBudget.setMaterialBudget(projectBudget.getMaterialCost()); // From ProjectBudget entity
	    } else {
	        clientBudget.setMaterialBudget(null);
	    }

	    // Set the associated ProjectBudget in the ClientBudget
	    clientBudget.setProjectBudget(projectBudget); // Link the projectBudget to the clientBudget

	    // Save the client budget
	    clientBudgetRepository.save(clientBudget);

	    // Update the project to confirm the budget selection
	    project.setConfirmed(true); // Set the confirmed field to true
	    project.setStatus("CONFIRMED"); // Set the status to "CONFIRMED"

	    // Save the updated project
	    projectRepository.save(project);
	}



	// New method to get final budget for a project
	public BigDecimal getFinalBudget(Long projectId) {
		// Fetch the ClientBudget for the given project
		ClientBudget clientBudget = clientBudgetRepository.findById(projectId)
				.orElseThrow(() -> new IllegalArgumentException("No client budget found for project ID: " + projectId));

		// Return the final budget
		return clientBudget.getFinalBudget();
	}

	// Method to get all ClientBudgets
	public List<ClientBudget> getAllClientBudgets() {
	    return clientBudgetRepository.findAll();
	}

	// New method to calculate the net profit for a project
	public BigDecimal calculateNetProfit(Long clientBudgetId) {
		// Fetch the ClientBudget for the given ID
		ClientBudget clientBudget = clientBudgetRepository.findById(clientBudgetId)
				.orElseThrow(() -> new IllegalArgumentException("ClientBudget not found for ID: " + clientBudgetId));

		// Retrieve necessary values
		BigDecimal finalBudget = clientBudget.getFinalBudget();
		BigDecimal managerEstimate = clientBudget.getManagerEstimate();
		BigDecimal materialBudget = clientBudget.isWithMaterials() ? clientBudget.getMaterialBudget() : BigDecimal.ZERO;

		// Calculate net profit
		BigDecimal netProfit = finalBudget.subtract(managerEstimate.add(materialBudget));

		return netProfit;
	}

	/**
	 * Calculates the net profit for all ClientBudgets in the database.
	 *
	 * @return a Map where the key is the ClientBudget ID and the value is the net
	 *         profit
	 */
	public Map<Long, BigDecimal> calculateAllNetProfits() {
		List<ClientBudget> clientBudgets = clientBudgetRepository.findAll();

		return clientBudgets.stream().collect(Collectors.toMap(ClientBudget::getId, // Key: ClientBudget ID
				clientBudget -> {
					// Calculate net profit for each ClientBudget
					BigDecimal finalBudget = clientBudget.getFinalBudget();
					BigDecimal managerEstimate = clientBudget.getManagerEstimate();
					BigDecimal materialBudget = clientBudget.isWithMaterials() ? clientBudget.getMaterialBudget()
							: BigDecimal.ZERO;

					return finalBudget.subtract(managerEstimate.add(materialBudget)); // Net profit formula
				}));
	}

	/**
	 * Calculates the total net profit for all ClientBudgets in the database.
	 *
	 * @return the total net profit as a BigDecimal
	 */
	public BigDecimal calculateTotalNetProfit() {
		List<ClientBudget> clientBudgets = clientBudgetRepository.findAll();

		return clientBudgets.stream().map(clientBudget -> {
			// Calculate net profit for each ClientBudget
			BigDecimal finalBudget = clientBudget.getFinalBudget();
			BigDecimal managerEstimate = clientBudget.getManagerEstimate();
			BigDecimal materialBudget = clientBudget.isWithMaterials() ? clientBudget.getMaterialBudget()
					: BigDecimal.ZERO;

			// Net profit formula
			return finalBudget.subtract(managerEstimate.add(materialBudget));
		}).reduce(BigDecimal.ZERO, BigDecimal::add); // Sum up all net profits
	}

//	public String getWithMaterialsStatus(Long projectId) {
//		// Fetch the ClientBudget for the given projectId
//		ClientBudget clientBudget = clientBudgetRepository.findByProjectId(projectId)
//				.orElseThrow(() -> new IllegalArgumentException("ClientBudget not found for project ID: " + projectId));
//
//		// Return "Yes" if withMaterials is true, otherwise "No"
//		return clientBudget.isWithMaterials() ? "Yes" : "No";
//	}

}
