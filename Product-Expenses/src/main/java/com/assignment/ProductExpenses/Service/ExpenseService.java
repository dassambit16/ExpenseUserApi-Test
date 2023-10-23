package com.assignment.ProductExpenses.Service;

import com.assignment.ProductExpenses.Model.Expenses;
import com.assignment.ProductExpenses.Model.User;
import com.assignment.ProductExpenses.Repo.IExpensesRepo;
import com.assignment.ProductExpenses.Repo.IUserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    IExpensesRepo expensesRepo;

    @Autowired
    IUserRepo iUserRepo;


    public String addExpenses(Expenses expenses) {
        User user = expenses.getUserId();
        iUserRepo.save(user);
        expenses.setDate(LocalDate.now());
        expenses.setTime(LocalTime.now());
        expensesRepo.save(expenses);
        return " Expenses added.";
    }


    public Expenses getExpense(Long id) {
        return expensesRepo.findById(id).orElseThrow();
    }

    public List<Expenses> getAllExpense() {
        return expensesRepo.findAll();
    }

    public List<Expenses> getAllExpensesByDate(LocalDate date) {
        return expensesRepo.findByDate(date);
    }

    //this is instruction to spring framework that tells it to schedule a method to run automatically
    // cron = is a special timer that define then method should be run
    // syntax( sec : min : hour : day : month : week)
    @Scheduled(cron = "0 57 17 * * *")
    @Transactional
    public void deleteOldExpense() {
        LocalDate threeMonths = LocalDate.now().minusMonths(3);
        expensesRepo.deleteByDateBefore(threeMonths);
    }
}
