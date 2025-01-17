package com.cyfrifpro.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProfitMarginRequestDTO {
	
	private Long id;
    private Long estimateId;
    private BigDecimal profitMarginPercentage;

    // Getters and Setters
}

