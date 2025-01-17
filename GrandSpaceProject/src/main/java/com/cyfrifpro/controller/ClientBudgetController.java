package com.cyfrifpro.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyfrifpro.model.BudgetOption;
import com.cyfrifpro.model.ClientBudget;
import com.cyfrifpro.service.ClientBudgetService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/client-budget")
@CrossOrigin(origins = "http://127.0.0.1:5500/")
@Tag(name = "ClientBudgetController", description = "By using this class we can map all kinds of client budget requests.")
public class ClientBudgetController {

	@Autowired
	private ClientBudgetService clientBudgetService;

	/**
	 * Endpoint for the client to confirm their selected budget option.
	 * 
	 * @param projectId           The project ID for which the budget is being
	 *                            confirmed.
	 * @param clientBudgetRequest The client's selected budget option.
	 * @return Response indicating success or failure.
	 */
	@PostMapping("/confirm/{projectBudgetId}")
	public ResponseEntity<String> confirmBudgetSelection(@PathVariable Long projectBudgetId,
			@RequestBody BudgetOption selectedOption) {
		try {
			clientBudgetService.confirmBudgetSelection(projectBudgetId, selectedOption);
			return ResponseEntity.ok("Budget selection confirmed successfully.");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body("Error: " + e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(500).body("An unexpected error occurred: " + e.getMessage());
		}
	}

	// Endpoint to get the final budget for a specific project
	@GetMapping("/{projectId}/final-budget")
	public ResponseEntity<BigDecimal> getFinalBudget(@PathVariable Long projectId) {
		try {
			// Fetch the final budget for the given projectId
			BigDecimal finalBudget = clientBudgetService.getFinalBudget(projectId);

			// Return the final budget as response
			return ResponseEntity.ok(finalBudget);
		} catch (IllegalArgumentException e) {
			// Handle case when no client budget is found for the project
			return ResponseEntity.notFound().build();
		}
	}

	// Endpoint to fetch all ClientBudgets
	@GetMapping("getAll")
    public List<ClientBudget> getAllClientBudgets() {
        return clientBudgetService.getAllClientBudgets();
    }

	@GetMapping("/{clientBudgetId}/netProfit")
	public ResponseEntity<BigDecimal> getNetProfit(@PathVariable Long clientBudgetId) {
		BigDecimal netProfit = clientBudgetService.calculateNetProfit(clientBudgetId);
		return ResponseEntity.ok(netProfit);
	}

	@GetMapping("/all/netProfits")
	public ResponseEntity<Map<Long, BigDecimal>> getAllNetProfits() {
		Map<Long, BigDecimal> netProfits = clientBudgetService.calculateAllNetProfits();
		return ResponseEntity.ok(netProfits);
	}

	@GetMapping("/totalNetProfit")
	public ResponseEntity<BigDecimal> getTotalNetProfit() {
		BigDecimal totalNetProfit = clientBudgetService.calculateTotalNetProfit();
		return ResponseEntity.ok(totalNetProfit);
	}

	// Endpoint to get "Yes" or "No" based on withMaterials flag
//	@GetMapping("/{projectId}/with-materials-status")
//	public ResponseEntity<String> getWithMaterialsStatus(@PathVariable Long projectId) {
//		try {
//			// Fetch the status for withMaterials (Yes/No)
//			String withMaterialsStatus = clientBudgetService.getWithMaterialsStatus(projectId);
//
//			// Return the status as response
//			return ResponseEntity.ok(withMaterialsStatus);
//		} catch (IllegalArgumentException e) {
//			// Handle case when no client budget is found for the project
//			return ResponseEntity.notFound().build();
//		}
//	}

}
