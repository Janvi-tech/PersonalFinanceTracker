package com.example.financeTracker.dto;

public class BudgetSummary {

    private String category;
    private double budgetAmount;
    private double spentAmount;
    private double remainingAmount;
    private double utilizationPercentage;

    public BudgetSummary(
            String category,
            double budgetAmount,
            double spentAmount) {

        this.category = category;
        this.budgetAmount = budgetAmount;
        this.spentAmount = spentAmount;

        this.remainingAmount =
                budgetAmount - spentAmount;

        if (budgetAmount > 0) {
            this.utilizationPercentage =
                    (spentAmount / budgetAmount) * 100;
        }
    }

    public String getCategory() {
        return category;
    }

    public double getBudgetAmount() {
        return budgetAmount;
    }

    public double getSpentAmount() {
        return spentAmount;
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public double getUtilizationPercentage() {
        return utilizationPercentage;
    }
}