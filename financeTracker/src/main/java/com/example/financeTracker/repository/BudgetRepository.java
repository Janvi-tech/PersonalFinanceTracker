package com.example.financeTracker.repository;

import com.example.financeTracker.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.financeTracker.entity.User;
import java.util.List;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
	 List<Budget> findByUser(User user);

	    Optional<Budget> findByIdAndUser(
	            Long id,
	            User user
	    );
}
