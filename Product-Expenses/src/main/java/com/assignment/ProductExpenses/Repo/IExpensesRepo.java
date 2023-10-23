package com.assignment.ProductExpenses.Repo;

import com.assignment.ProductExpenses.Model.Expenses;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface IExpensesRepo extends JpaRepository<Expenses, Long> {
    List<Expenses> findByDate(LocalDate date);

    @Transactional
    void deleteByDateBefore(LocalDate threeMonths);

//    @Modifying
//    @Query
//    List<Expenses> productsByDates(LocalDate date);
}
