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

    public List<Expense> findAllOrderByDateDesc() {
        return expenseRepository.findAllByOrderByDateDesc();
    }

    public Expense save(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Expense getExpenseById(int id) {
        return expenseRepository.findById(id).orElse(null);
    }

    public void deleteExpense(int id) {
        expenseRepository.deleteById(id);
        expenseRepository.flush();
    }

    public void updateExpense(int id, Expense expense) {
        Expense existing = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found"));
        existing.setDate(expense.getDate());
        existing.setAmount(expense.getAmount());
        existing.setCategory(expense.getCategory());
        existing.setNote(expense.getNote());
        expenseRepository.save(existing);
    }
}
