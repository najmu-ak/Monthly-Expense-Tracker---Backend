package com.example.expense.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.expense.entity.Expense;
import com.example.expense.repository.ExpenseRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

 
    // Get current month expenses
    public List<Expense> getCurrentMonthExpenses() {
        int currentMonth = LocalDate.now().getMonthValue();
        return expenseRepository.findByMonth(currentMonth);
    }

    // Get expenses by any month
    public List<Expense> getExpensesByMonth(int month) {
        return expenseRepository.findByMonth(month);
    }

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

    public List<Expense> filterExpenses(String category, LocalDate date, Integer month) {
        if (month != null) {
            return expenseRepository.findByMonth(month);
        } else if (category != null && date != null) {
            return expenseRepository.findByCategoryAndDate(category, date);
        } else if (category != null) {
            return expenseRepository.findByCategory(category);
        } else if (date != null) {
            return expenseRepository.findByDate(date);
        }
        return expenseRepository.findAll();
    }

}
