package com.cyfrifpro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cyfrifpro.model.PaymentPlan;

@Repository
public interface PaymentPlanRepository extends JpaRepository<PaymentPlan, Long> {
	List<PaymentPlan> findByClientBudgetId(Long clientBudgetId);
	
	 Optional<PaymentPlan> findByClientBudgetIdAndPhase(Long clientBudgetId, int phase);
}
