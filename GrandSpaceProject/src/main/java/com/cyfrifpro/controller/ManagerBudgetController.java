package com.cyfrifpro.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyfrifpro.model.ManagerBudget;
import com.cyfrifpro.request.ManagerBudgetRequest;
import com.cyfrifpro.service.ManagerBudgetService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/projectS")
@CrossOrigin(origins = "http://127.0.0.1:5500/")
@Tag(name = "ManagerBudgetController", description = "By using this class we can map all kind of manager budget requests.")
public class ManagerBudgetController {

	@Autowired
	private ManagerBudgetService managerBudgetService;

	// Endpoint for managers to submit their estimated budget
	// Endpoint for managers to submit their estimated budget
	@PostMapping("/{projectId}/budget/submit")
	@Operation(summary = "Post Api", description = "This is a method for manager, where he can sumbit the estimate amount of that project")
	public ResponseEntity<ManagerBudget> submitBudget(@PathVariable Long projectId,
			@RequestBody ManagerBudgetRequest managerBudgetRequest) {

		// Call the service to process the budget submission
		ManagerBudget managerBudget = managerBudgetService.submitBudget(projectId, managerBudgetRequest.getManagerId(),
				managerBudgetRequest.getEstimatedBudget());

		// Return the created budget record with status 201 (Created)
		return ResponseEntity.status(HttpStatus.CREATED).body(managerBudget);
	}

	// Endpoint to get the total number of project estimates
	@GetMapping("/project-estimates/count")
	public long getTotalEstimates() {
		return managerBudgetService.getTotalEstimates();
	}

	// Endpoint for admin to fetch all budget estimates for a project
//	@GetMapping("/{projectId}/budget/estimates")
//	@Operation(summary = "Get Api", description = "This is a method for getting all the estimates of managers")
//	public ResponseEntity<List<ManagerBudget>> getBudgetEstimates(@PathVariable Long projectId) {
//		List<ManagerBudget> managerBudgets = managerBudgetService.getBudgetsByProject(projectId);
//		return ResponseEntity.status(HttpStatus.OK).body(managerBudgets);
//	}

	// Endpoint to fetch all project budget estimates
	@GetMapping("/budgets")
	public List<ManagerBudget> getAllBudgets() {
		return managerBudgetService.getAllBudgets();
	}

	// Endpoint to get the estimated budget for a specific project by manager
	@GetMapping("/budget/{projectId}/{managerId}")
	public BigDecimal getEstimatedBudget(@PathVariable Long projectId, @PathVariable Long managerId) {
		return managerBudgetService.getEstimatedBudget(projectId, managerId);
	}

}
