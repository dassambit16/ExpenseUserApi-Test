package com.assignment.ProductExpenses.Controller;

import com.assignment.ProductExpenses.Model.Expenses;
import com.assignment.ProductExpenses.Service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ExpenseController {
    @Autowired
    ExpenseService expenseService;

    @PostMapping("expenses")
    public String addExpenses(@RequestBody Expenses expenses) {
        return expenseService.addExpenses(expenses);
    }

    @GetMapping("expense/{id}")
    public Expenses getExpense(@PathVariable Long id) {
        return expenseService.getExpense(id);
    }

    @GetMapping("expenses")
    public List<Expenses> getAllExpense() {
        return expenseService.getAllExpense();
    }

    @GetMapping("{date}/expenses")
    public List<Expenses> getAllExpensesByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return expenseService.getAllExpensesByDate(date);
    }
}
