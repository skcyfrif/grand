package com.cyfrifpro.model;

//import jakarta.persistence.Entity;

//@Entity
public class BudgetOption {

	private boolean withMaterials;

	public boolean isWithMaterials() {
		return withMaterials;
	}

	public void setWithMaterials(boolean withMaterials) {
		this.withMaterials = withMaterials;
	}

	public BudgetOption(boolean withMaterials) {
		super();
		this.withMaterials = withMaterials;
	}

	public BudgetOption() {
		super();
		// TODO Auto-generated constructor stub
	}

}
