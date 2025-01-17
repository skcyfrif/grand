package com.cyfrifpro.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class EstimateResponseDTO {

	private Long id;
    private BigDecimal managerFinalBudget;
    private int estimatedTimeInWeeks;
    private BigDecimal amountPerSqFt;
    private BigDecimal areaInSquareFeet; // Snapshot of project area at the time of estimate

}
