package com.example.financeTracker.controller;

import com.example.financeTracker.entity.Expense;
import com.example.financeTracker.entity.User;
import com.example.financeTracker.repository.ExpenseRepository;
import com.example.financeTracker.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseController(
            ExpenseRepository expenseRepository,
            UserRepository userRepository) {

        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }


    // Display only logged-in user's expenses
    @GetMapping
    public String showExpensePage(
            Model model,
            Authentication authentication) {

        User user = getLoggedInUser(authentication);

        model.addAttribute(
                "expenseList",
                expenseRepository.findByUser(user)
        );

        model.addAttribute(
                "expense",
                new Expense()
        );

        return "expenses";
    }


    // Add expense
    @PostMapping("/add")
    public String addExpense(
            @ModelAttribute Expense expense,
            Authentication authentication) {

        User user = getLoggedInUser(authentication);

        // Connect expense to logged-in user
        expense.setUser(user);

        expenseRepository.save(expense);

        return "redirect:/expenses";
    }


    // Show edit page
    @GetMapping("/edit/{id}")
    public String showEditPage(
            @PathVariable Long id,
            Model model,
            Authentication authentication) {

        User user = getLoggedInUser(authentication);

        Expense expense =
                expenseRepository
                .findByIdAndUser(id, user)
                .orElseThrow();

        model.addAttribute(
                "expense",
                expense
        );

        return "edit-expense";
    }


    // Update expense
    @PostMapping("/update")
    public String updateExpense(
            @ModelAttribute Expense expense,
            Authentication authentication) {

        User user = getLoggedInUser(authentication);

        Expense existingExpense =
                expenseRepository
                .findByIdAndUser(
                        expense.getId(),
                        user
                )
                .orElseThrow();

        existingExpense.setCategory(
                expense.getCategory()
        );

        existingExpense.setAmount(
                expense.getAmount()
        );

        existingExpense.setExpenseDate(
                expense.getExpenseDate()
        );

        existingExpense.setDescription(
                expense.getDescription()
        );

        expenseRepository.save(existingExpense);

        return "redirect:/expenses";
    }


    // Delete expense
    @GetMapping("/delete/{id}")
    public String deleteExpense(
            @PathVariable Long id,
            Authentication authentication) {

        User user = getLoggedInUser(authentication);

        Expense expense =
                expenseRepository
                .findByIdAndUser(id, user)
                .orElseThrow();

        expenseRepository.delete(expense);

        return "redirect:/expenses";
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