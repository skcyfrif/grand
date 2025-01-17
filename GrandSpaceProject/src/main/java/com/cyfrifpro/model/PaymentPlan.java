package com.cyfrifpro.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class PaymentPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "client_budget_id", nullable = false)
	private ClientBudget clientBudget;

	private int phase; // Phase number (1-5)
	private BigDecimal amountDue; // Amount for this phase
	private LocalDate dueDate; // Due date for this phase
	private boolean paid; // Whether the payment has been made

	// You can also store the payment date, transaction details, etc.
}