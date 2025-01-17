package com.cyfrifpro.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProjectBudget {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;

	@ManyToOne
	@JoinColumn(name = "manager_id", nullable = false)
	private Manager manager; // New field to store the manager who gave the estimated budget

	@ManyToOne
	@JoinColumn(name = "managerbudget_id", nullable = false)
	private ManagerBudget managerBudget;

	private BigDecimal budgetWithMaterials;
	private BigDecimal budgetWithoutMaterials;
	private BigDecimal materialCost;
	private BigDecimal profitMargin;
	
	@Column(nullable = false)
	private boolean publish = false; // Default value is false

	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	private Client client; // Reference to the Client

	public ProjectBudget(Project project, Manager manager, ManagerBudget managerBudget, BigDecimal estimatedBudget,
			BigDecimal materialCost, BigDecimal profitMargin) {
		this.project = project;
		this.manager = manager;
		this.managerBudget = managerBudget;// Set the manager who provided the budget
		this.materialCost = materialCost;
		this.profitMargin = profitMargin;

		// Calculate budgets
		this.budgetWithMaterials = estimatedBudget.add(materialCost)
				.add((estimatedBudget.add(materialCost)).multiply(profitMargin.divide(BigDecimal.valueOf(100))));

		this.budgetWithoutMaterials = estimatedBudget
				.add(estimatedBudget.multiply(profitMargin.divide(BigDecimal.valueOf(100))));
	}
}
