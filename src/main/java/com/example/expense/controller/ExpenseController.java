package com.example.expense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.expense.entity.Expense;
import com.example.expense.service.ExpenseService;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") // Allow requests from the React frontend
@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/add")
    public Expense addExpense(@RequestBody Expense expense) {
        return expenseService.save(expense);
    }

    @PutMapping("/update/{id}")
    public String updateExpense(@PathVariable int id, @RequestBody Expense expense) {
        expenseService.updateExpense(id, expense);
        return "Expense updated successfully";
    }

    @GetMapping("/all")
    public List<Expense> getAllExpenses() {
        return expenseService.findAllOrderByDateDesc();
    }

    @GetMapping("/{id}")
    public Expense getByExpenseId(@PathVariable int id) {
        Expense expense = expenseService.getExpenseById(id);
        if (expense != null) {
            return expense;
        } else {
            throw new RuntimeException("Expense not found with id: " + id);
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteExpense(@PathVariable int id) {
        expenseService.deleteExpense(id);
        return "Expense deleted successfully";
    }

    @GetMapping("/filter")
    public List<Expense> filterExpenses(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) Integer month) {
        return expenseService.filterExpenses(category, date, month);
    }

    // Default - Current month
    @GetMapping("/current-month")
    public List<Expense> getCurrentMonthExpenses() {
        return expenseService.getCurrentMonthExpenses();
    }

    // Filter by month
    @GetMapping("/month/{month}")
    public List<Expense> getExpensesByMonth(@PathVariable int month) {
        return expenseService.getExpensesByMonth(month);
    }

}
