package com.example.expense.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.expense.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    List<Expense> findAllByOrderByDateDesc();

    List<Expense> findByCategory(String category);

    List<Expense> findByDate(LocalDate date);

    List<Expense> findByCategoryAndDate(String category, LocalDate date);

   @Query(value = "SELECT * FROM expense WHERE MONTH(STR_TO_DATE(date, '%Y-%m-%d')) = :month", nativeQuery = true)
List<Expense> findByMonth(@Param("month") int month);


}
