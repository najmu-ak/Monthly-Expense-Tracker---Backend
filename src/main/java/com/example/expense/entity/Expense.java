package com.example.expense.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Expense {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "amount")
    private double amount;

    @Column(name = "category")
    private String category;

    @Column(name = "note")
    private String note;

    // ✅ Required by JPA and Jackson
    public Expense() {
    }

    public Expense(int id, LocalDate date, double amount, String category, String note) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.note = note;
    }
}
