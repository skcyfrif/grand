package com.cyfrifpro.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyfrifpro.model.ClientBudget;
import com.cyfrifpro.model.PaymentPlan;
import com.cyfrifpro.repository.ClientBudgetRepository;
import com.cyfrifpro.repository.PaymentPlanRepository;

@Service
public class PaymentPlanService {

	@Autowired
	private PaymentPlanRepository paymentPlanRepository;

	@Autowired
	private ClientBudgetRepository clientBudgetRepository;

	public void generatePaymentPlan(ClientBudget clientBudget) {
		BigDecimal finalBudget = clientBudget.getFinalBudget();
		BigDecimal phaseAmount = finalBudget.divide(BigDecimal.valueOf(5), 2, BigDecimal.ROUND_HALF_UP); // Divide by 5
																											// for 5
																											// phases

		LocalDate startDate = LocalDate.now(); // You can set a start date here
		List<PaymentPlan> paymentPlans = new ArrayList<>();

		for (int i = 1; i <= 5; i++) {
			PaymentPlan paymentPlan = new PaymentPlan();
			paymentPlan.setClientBudget(clientBudget);
			paymentPlan.setPhase(i);
			paymentPlan.setAmountDue(phaseAmount);
			paymentPlan.setDueDate(startDate.plusMonths(i)); // Each phase due a month later
			paymentPlan.setPaid(false); // Mark as not paid initially

			paymentPlans.add(paymentPlan);
		}

		paymentPlanRepository.saveAll(paymentPlans); // Save all the generated payment plans
	}

	public BigDecimal getFinalBudget(Long clientBudgetId) {
		// Fetch the ClientBudget by its ID
		ClientBudget clientBudget = clientBudgetRepository.findById(clientBudgetId)
				.orElseThrow(() -> new RuntimeException("ClientBudget not found"));

		// Return the final budget associated with the ClientBudget
		return clientBudget.getFinalBudget();
	}

	public BigDecimal getTotalPaidAmount(Long clientBudgetId) {
		// Fetch the ClientBudget by its ID
		ClientBudget clientBudget = clientBudgetRepository.findById(clientBudgetId)
				.orElseThrow(() -> new RuntimeException("ClientBudget not found"));

		// Fetch all payment plans for the given ClientBudget
		List<PaymentPlan> paymentPlans = paymentPlanRepository.findByClientBudgetId(clientBudgetId);

		// Calculate the total paid amount
		BigDecimal totalPaidAmount = BigDecimal.ZERO;

		for (PaymentPlan paymentPlan : paymentPlans) {
			if (paymentPlan.isPaid()) {
				totalPaidAmount = totalPaidAmount.add(paymentPlan.getAmountDue());
			}
		}

		return totalPaidAmount;
	}

	public long countPaidPhases(Long clientBudgetId) {
		// Fetch the ClientBudget by its ID
		ClientBudget clientBudget = clientBudgetRepository.findById(clientBudgetId)
				.orElseThrow(() -> new RuntimeException("ClientBudget not found"));

		// Fetch all payment plans for the given ClientBudget
		List<PaymentPlan> paymentPlans = paymentPlanRepository.findByClientBudgetId(clientBudgetId);

		// Count the number of paid phases
		long paidPhasesCount = paymentPlans.stream().filter(PaymentPlan::isPaid) // Filter only the phases that are paid
				.count(); // Count the number of paid phases

		return paidPhasesCount;
	}

	public BigDecimal getRemainingAmount(Long clientBudgetId) {
		// Fetch the ClientBudget by its ID
		ClientBudget clientBudget = clientBudgetRepository.findById(clientBudgetId)
				.orElseThrow(() -> new RuntimeException("ClientBudget not found"));

		// Fetch all payment plans for the given ClientBudget
		List<PaymentPlan> paymentPlans = paymentPlanRepository.findByClientBudgetId(clientBudgetId);

		// Calculate the remaining amount (sum of the amountDue for unpaid phases)
		BigDecimal remainingAmount = paymentPlans.stream().filter(paymentPlan -> !paymentPlan.isPaid()) // Filter only
																										// unpaid phases
				.map(PaymentPlan::getAmountDue) // Map to the amountDue for each unpaid phase
				.reduce(BigDecimal.ZERO, BigDecimal::add); // Sum up all the amounts

		return remainingAmount;
	}

	public BigDecimal getTotalFinalBudget() {
		// Fetch all ClientBudgets from the repository
		List<ClientBudget> clientBudgets = clientBudgetRepository.findAll();

		// Calculate the total final budget by summing up the finalBudget of each
		// ClientBudget
		BigDecimal totalFinalBudget = clientBudgets.stream().map(ClientBudget::getFinalBudget) // Extract the
																								// finalBudget from each
																								// ClientBudget
				.filter(finalBudget -> finalBudget != null) // Ensure no null values are included
				.reduce(BigDecimal.ZERO, BigDecimal::add); // Sum all finalBudget values

		return totalFinalBudget;
	}

	public BigDecimal getTotalPaidAmountForAllProjects() {
		// Fetch all the ClientBudgets
		List<ClientBudget> clientBudgets = clientBudgetRepository.findAll();

		// Initialize total paid amount to zero
		BigDecimal totalPaidAmount = BigDecimal.ZERO;

		// Loop through all client budgets and sum the paid amounts
		for (ClientBudget clientBudget : clientBudgets) {
			// Fetch all payment plans for each ClientBudget
			List<PaymentPlan> paymentPlans = paymentPlanRepository.findByClientBudgetId(clientBudget.getId());

			// Sum up the paid amounts for each payment plan
			for (PaymentPlan paymentPlan : paymentPlans) {
				if (paymentPlan.isPaid()) {
					totalPaidAmount = totalPaidAmount.add(paymentPlan.getAmountDue());
				}
			}
		}

		// Return the total paid amount
		return totalPaidAmount;
	}

	public BigDecimal getTotalAmountToBePaidForAllProjects() {
		// Fetch all the ClientBudgets
		List<ClientBudget> clientBudgets = clientBudgetRepository.findAll();

		// Initialize total amount to be paid to zero
		BigDecimal totalAmountToBePaid = BigDecimal.ZERO;

		// Loop through all client budgets and sum the amount to be paid
		for (ClientBudget clientBudget : clientBudgets) {
			// Fetch all payment plans for each ClientBudget
			List<PaymentPlan> paymentPlans = paymentPlanRepository.findByClientBudgetId(clientBudget.getId());

			// Sum up the amount due for unpaid phases
			for (PaymentPlan paymentPlan : paymentPlans) {
				if (!paymentPlan.isPaid()) { // Only consider unpaid phases
					totalAmountToBePaid = totalAmountToBePaid.add(paymentPlan.getAmountDue());
				}
			}
		}

		// Return the total amount to be paid
		return totalAmountToBePaid;
	}

	public List<PaymentPlan> getAllPaymentPlans() {
		return paymentPlanRepository.findAll();
	}

}
