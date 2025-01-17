package com.cyfrifpro.request;

import java.math.BigDecimal;

import com.cyfrifpro.model.Manager;

import lombok.Data;

@Data
public class AdminBudgetRequest {
	private Long managerBudgetId; // The selected manager's budget ID
	private BigDecimal materialCost; // Material cost added by admin
	private BigDecimal profitMargin; // Profit margin to be added
//	private Manager managerId;


	// Getters and setters
}
