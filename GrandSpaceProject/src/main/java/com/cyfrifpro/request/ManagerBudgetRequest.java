package com.cyfrifpro.request;


import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ManagerBudgetRequest {

    private Long managerId;
    private BigDecimal estimatedBudget;
	private LocalDate uploadDate;
	private String status;


    // Getters and Setters
}

