package com.example.expense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.expense.entity.Expense;
import com.example.expense.service.ExpenseService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") // Allow requests from the React frontend
@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/add")
    public Expense addExpense(@RequestBody Expense expense) {
        return expenseService.save(expense); // <-- return saved expense
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
    public List<Expense> filterExpenses(@RequestParam(required = false) String category,
            @RequestParam(required = false) String date) {
        return expenseService.filterExpenses(category, date);
    }
}
