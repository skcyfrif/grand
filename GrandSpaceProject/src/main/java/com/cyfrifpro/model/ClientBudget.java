package com.cyfrifpro.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ClientBudget {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;

	private boolean withMaterials; // Client's selection: true for with materials, false for without
	private BigDecimal finalBudget; // Final budget based on the client's selection

	// Transient fields for additional information
	private String projectName; // Project name (derived from Project entity)

	private BigDecimal clientProjectBudget; // Client's initial project budget (derived from Project entity)

	private BigDecimal managerEstimate; // Manager's estimated budget (derived from Manager entity)

	private BigDecimal materialBudget; // Material cost (derived from ProjectBudget or other entities)
	
	@ManyToOne
	@JoinColumn(name = "project_budget_id", nullable = false)
	private ProjectBudget projectBudget;

}