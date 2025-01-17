package com.cyfrifpro.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.cyfrifpro.model.Client;
import com.cyfrifpro.model.Manager;
import com.cyfrifpro.model.ProjectBudget;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProjectResponse {

	private Long id;
	private String name;
	private String description;
	private BigDecimal budget;
	private BigDecimal areaInSquareFeet;
	private Client client; // Nested DTO for Client
	private Manager manager; // Nested DTO for Manager
	private boolean confirmed;
	private String status; // e.g., IN_PROGRESS, COMPLETED, CONFIRMED
    private LocalDate registrationDate; // Field for registration date
    private LocalDate estimatedByVendor; // Field for estimated by vendor date
	private ProjectBudget projectBudget;


}
