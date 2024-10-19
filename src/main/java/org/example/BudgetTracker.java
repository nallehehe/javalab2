package org.example;

import java.io.IOException;
import java.util.Scanner;

public class BudgetTracker {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        Menu menu = new Menu();
        ExpenseStorage expenseStorage = new ExpenseStorage();


        int userInput = 0;

        while (true) {

            menu.budgetMenu();
            userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    Expense.createExpense(expenseStorage);
                    break;
                case 2:
                    //add income
                    break;
                case 3:
                    //show all expenses
                    break;
                case 4:
                    //show all incomes
                    break;
                case 5:
                    //update an expense
                    break;
                case 6:
                    //update an income
                    break;
                case 7:
                    //delete an expense
                    break;
                case 8:
                    //delete an income
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }
}