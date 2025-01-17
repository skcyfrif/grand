package com.cyfrifpro.request;

import com.cyfrifpro.model.BudgetOption;

import lombok.Data;

@Data
public class BudgetOptionRequest {
	private Boolean withMaterials; // true if the client selects budget with materials, false for without materials

	// Converts the request to a BudgetOption model
	public BudgetOption toBudgetOption() {
		return new BudgetOption(withMaterials);
	}
}