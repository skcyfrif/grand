package com.cyfrifpro.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AdminProfitMarginResponseDTO {

	private Long id;
	private Long estimateId;
	private BigDecimal profitMarginPercentage;
	private BigDecimal finalBudgetWithProfitMargin;
	private BigDecimal managerFinalBudget;
	private int estimatedTimeInWeeks;

}
