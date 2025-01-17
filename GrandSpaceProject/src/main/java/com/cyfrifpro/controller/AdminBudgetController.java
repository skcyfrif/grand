package com.cyfrifpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cyfrifpro.model.ProjectBudget;
import com.cyfrifpro.request.AdminBudgetRequest;
import com.cyfrifpro.service.AdminBudgetService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://127.0.0.1:5500")
@Tag(name = "AdminBudgetController", description = "By using this class we can map all kind of admin budget requests.")
public class AdminBudgetController {

	@Autowired
	private AdminBudgetService adminBudgetService;

	@PostMapping("/project/{projectId}/select-budget")
	@Operation(summary = "Finalize Budget", description = "Admin finalizes the budget for a project and submits the final amount.")
	public ResponseEntity<?> selectBudget(@PathVariable Long projectId,
			@RequestBody AdminBudgetRequest adminBudgetRequest) {

		try {
			// Validate input parameters (if needed)
			if (adminBudgetRequest.getManagerBudgetId() == null) {
				return ResponseEntity.badRequest().body("ManagerBudgetId is required.");
			}
			if (adminBudgetRequest.getMaterialCost() == null) {
				return ResponseEntity.badRequest().body("Material cost is required.");
			}
			if (adminBudgetRequest.getProfitMargin() == null) {
				return ResponseEntity.badRequest().body("Profit margin is required.");
			}

			// Call service to create project budget
			ProjectBudget projectBudget = adminBudgetService.createProjectBudget(projectId,
					adminBudgetRequest.getManagerBudgetId(), adminBudgetRequest.getMaterialCost(),
					adminBudgetRequest.getProfitMargin());

			// Return the finalized project budget
			return ResponseEntity.ok(projectBudget);
		} catch (IllegalArgumentException e) {
			// Handle cases like invalid ManagerBudgetId
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			// Handle unexpected errors
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while finalizing the budget: " + e.getMessage());
		}
	}

	// Method to update publish status automatically
	@PutMapping("/{id}/publish")
	public ResponseEntity<ProjectBudget> updatePublishStatus(@PathVariable("id") Long projectBudgetId) {
		// Update publish status using the service
		ProjectBudget updatedProjectBudget = adminBudgetService.updatePublishStatus(projectBudgetId);

		// Return the updated ProjectBudget as a response
		return ResponseEntity.ok(updatedProjectBudget);
	}

	// Endpoint to retrieve ProjectBudgets by clientId where publish is true
	@GetMapping("/client/{clientId}")
	public List<ProjectBudget> getProjectBudgetsByClientId(@PathVariable Long clientId) {
		return adminBudgetService.getProjectBudgetsByClientId(clientId);
	}

	// Method to retrieve the project budget with both options
	@GetMapping("/project/{projectId}/budgets")
	public ResponseEntity<ProjectBudget> getBudgets(@PathVariable Long projectId) {
		ProjectBudget projectBudget = adminBudgetService.getProjectBudget(projectId);
		return ResponseEntity.ok(projectBudget);
	}

	// Endpoint to fetch all project budgets
	@GetMapping("/project-budgets")
	public List<ProjectBudget> getAllProjectBudgets() {
		return adminBudgetService.getAllProjectBudgets(); // Fetch all project budgets
	}
}