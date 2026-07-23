package com.example.financeTracker.controller;

import com.example.financeTracker.entity.Income;
import com.example.financeTracker.entity.User;
import com.example.financeTracker.repository.IncomeRepository;
import com.example.financeTracker.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/income")
public class IncomeController {

    private final IncomeRepository incomeRepository;
    private final UserRepository userRepository;

    public IncomeController(
            IncomeRepository incomeRepository,
            UserRepository userRepository) {

        this.incomeRepository = incomeRepository;
        this.userRepository = userRepository;
    }


    // Display only logged-in user's income
    @GetMapping
    public String showIncomePage(
            Model model,
            Authentication authentication) {

        User user = getLoggedInUser(authentication);

        model.addAttribute(
                "incomeList",
                incomeRepository.findByUser(user)
        );

        model.addAttribute(
                "income",
                new Income()
        );

        return "income";
    }


    // Add income
    @PostMapping("/add")
    public String addIncome(
            @ModelAttribute Income income,
            Authentication authentication) {

        User user = getLoggedInUser(authentication);

        // Important: connect income to logged-in user
        income.setUser(user);

        incomeRepository.save(income);

        return "redirect:/income";
    }


    // Show edit page
    @GetMapping("/edit/{id}")
    public String showEditPage(
            @PathVariable Long id,
            Model model,
            Authentication authentication) {

        User user = getLoggedInUser(authentication);

        Income income =
                incomeRepository
                .findByIdAndUser(id, user)
                .orElseThrow();

        model.addAttribute(
                "income",
                income
        );

        return "edit-income";
    }


    // Update income
    @PostMapping("/update")
    public String updateIncome(
            @ModelAttribute Income income,
            Authentication authentication) {

        User user = getLoggedInUser(authentication);

        Income existingIncome =
                incomeRepository
                .findByIdAndUser(
                        income.getId(),
                        user
                )
                .orElseThrow();

        existingIncome.setSource(
                income.getSource()
        );

        existingIncome.setAmount(
                income.getAmount()
        );

        existingIncome.setIncomeDate(
                income.getIncomeDate()
        );

        existingIncome.setDescription(
                income.getDescription()
        );

        incomeRepository.save(existingIncome);

        return "redirect:/income";
    }


    // Delete income
    @GetMapping("/delete/{id}")
    public String deleteIncome(
            @PathVariable Long id,
            Authentication authentication) {

        User user = getLoggedInUser(authentication);

        Income income =
                incomeRepository.findByIdAndUser(id, user).orElseThrow();

        incomeRepository.delete(income);

        return "redirect:/income";
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