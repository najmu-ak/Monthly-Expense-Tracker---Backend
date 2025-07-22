package com.example.expense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.expense.repository")
public class ExpenseApplication {

  public static void main(String[] args) {
    SpringApplication.run(ExpenseApplication.class, args);
    System.out.println("Expense Application is running...");
  }

}