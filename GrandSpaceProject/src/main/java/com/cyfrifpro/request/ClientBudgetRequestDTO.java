package com.cyfrifpro.request;

import java.math.BigDecimal;

import com.cyfrifpro.model.BudgetOption;
import com.cyfrifpro.model.Manager;
import com.cyfrifpro.model.ProjectBudget;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ClientBudgetRequestDTO {
	private Long id;
	private String projectName;
	private BigDecimal clientProjectBudget;
	private BigDecimal managerEstimate;
	private BigDecimal materialBudget;
	private boolean withMaterials;
	private BigDecimal finalBudget;
	private ProjectBudget projectBudget;

	
	// Getters and Setters

	public Boolean getWithMaterials() {
		return withMaterials;
	}

	public void setWithMaterials(Boolean withMaterials) {
		this.withMaterials = withMaterials;
	}

	// Convert the request to a BudgetOption
	public BudgetOption toBudgetOption() {
		BudgetOption option = new BudgetOption();
		option.setWithMaterials(this.withMaterials);
		return option;
	}
}