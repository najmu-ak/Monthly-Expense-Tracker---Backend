package com.example.expense.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.expense.entity.Expense;
import com.example.expense.repository.ExpenseRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Expense getExpenseById(int id) {
        return expenseRepository.findById(id).orElse(null);
    }

    public void deleteExpense(int id) {
        expenseRepository.deleteById(id);
        expenseRepository.flush();
        System.out.println("Expense with id " + id + " deleted successfully");
    }

    public void updateExpense(int id, Expense expense) {
        if (expenseRepository.existsById(id)) {
            expense.setId(id); // Ensure the ID is set for the update
            expenseRepository.save(expense);
            expenseRepository.flush();
            System.out.println("Expense with id " + id + " updated successfully");
        } else {
            throw new RuntimeException("Expense not found with id: " + id);
        }
    }
}
