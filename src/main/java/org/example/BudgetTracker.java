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
            scanner.nextLine();

            switch (userInput) {
                case 1:
                    expenseStorage.createExpense();
                    break;
                case 2:
                    //
                    break;
                case 3:
                    expenseStorage.allExpenses();
                    break;
                case 4:
                    //show all incomes
                    break;
                case 5:
                    expenseStorage.updateExpense();
                    break;
                case 6:
                    //update an income
                    break;
                case 7:
                    expenseStorage.deleteExpense();
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