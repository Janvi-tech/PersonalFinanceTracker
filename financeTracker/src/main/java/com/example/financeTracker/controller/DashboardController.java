package com.example.financeTracker.controller;

import com.example.financeTracker.entity.User;
import com.example.financeTracker.repository.ExpenseRepository;
import com.example.financeTracker.repository.IncomeRepository;
import com.example.financeTracker.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DashboardController {

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;


    public DashboardController(
            IncomeRepository incomeRepository,
            ExpenseRepository expenseRepository,
            UserRepository userRepository) {

        this.incomeRepository = incomeRepository;
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }


    // Main Dashboard
    @GetMapping("/dashboard")
    public String showDashboard(
            Model model,
            Authentication authentication) {

        User user =
                getLoggedInUser(authentication);


        Double totalIncome =
                incomeRepository.getTotalIncome(user);


        Double totalExpense =
                expenseRepository.getTotalExpense(user);


        Double balance =
                totalIncome - totalExpense;


        List<Object[]> expenseData =
                expenseRepository
                .getExpenseByCategory(user);


        model.addAttribute(
                "totalIncome",
                totalIncome
        );

        model.addAttribute(
                "totalExpense",
                totalExpense
        );

        model.addAttribute(
                "balance",
                balance
        );

        model.addAttribute(
                "expenseData",
                expenseData
        );


        return "dashboard";
    }


    // Monthly Dashboard
    @GetMapping("/dashboard/monthly")
    public String showMonthlyDashboard(
            @RequestParam int month,
            @RequestParam int year,
            Model model,
            Authentication authentication) {

        User user = getLoggedInUser(authentication);

        Double monthlyIncome =
                incomeRepository.getMonthlyIncome(
                        month,
                        year,
                        user
                );

        Double monthlyExpense =
                expenseRepository.getMonthlyExpense(
                        month,
                        year,
                        user
                );

        Double balance =
                monthlyIncome - monthlyExpense;

        // ADD THIS
        List<Object[]> expenseData =
                expenseRepository.getExpenseByCategory(user);

        model.addAttribute("totalIncome", monthlyIncome);
        model.addAttribute("totalExpense", monthlyExpense);
        model.addAttribute("balance", balance);

        // ADD THIS
        model.addAttribute("expenseData", expenseData);

        model.addAttribute("selectedMonth", month);
        model.addAttribute("selectedYear", year);

        return "dashboard";
    }

    // Get logged-in user
    private User getLoggedInUser(
            Authentication authentication) {

        String username =
                authentication.getName();


        return userRepository
                .findByUsername(username)
                .orElseThrow();
    }
}