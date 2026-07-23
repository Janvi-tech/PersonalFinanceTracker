package com.example.financeTracker.controller;

import com.example.financeTracker.entity.Budget;
import com.example.financeTracker.entity.User;
import com.example.financeTracker.repository.BudgetRepository;
import com.example.financeTracker.repository.UserRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.financeTracker.dto.BudgetSummary;
import com.example.financeTracker.entity.Expense;
import com.example.financeTracker.repository.ExpenseRepository;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/budgets")
public class BudgetController {

    private final BudgetRepository budgetRepository;
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public BudgetController(
            BudgetRepository budgetRepository, ExpenseRepository expenseRepository, UserRepository userRepository) {

        this.budgetRepository = budgetRepository;
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }
    
    
    private User getLoggedInUser(
            Authentication authentication) {

        String username =
                authentication.getName();

        return userRepository
                .findByUsername(username)
                .orElseThrow();
    }

    @GetMapping
    public String showBudgetPage(Model model, Authentication authentication) {
    	
    	User user = getLoggedInUser(authentication);
        List<Budget> budgetList =
                budgetRepository.findAll();

        List<BudgetSummary> summaryList =
                new ArrayList<>();

        for (Budget budget : budgetList) {

            Double spent =
                    expenseRepository.getCategoryExpense(
                            budget.getCategory(),
                            budget.getMonth(),
                            budget.getYear(),
                            user
                    );

            BudgetSummary summary =
                    new BudgetSummary(
                            budget.getCategory(),
                            budget.getAmount(),
                            spent
                    );

            summaryList.add(summary);
        }

        model.addAttribute(
                "budget",
                new Budget()
        );

        model.addAttribute(
                "summaryList",
                summaryList
        );

        return "budgets";
    }

    @PostMapping("/add")
    public String addBudget(
            @ModelAttribute Budget budget) {

        budgetRepository.save(budget);

        return "redirect:/budgets";
    }

    @GetMapping("/delete/{id}")
    public String deleteBudget(
            @PathVariable Long id) {

        budgetRepository.deleteById(id);

        return "redirect:/budgets";
    }
}