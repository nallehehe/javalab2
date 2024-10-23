package org.example;

import java.io.IOException;
import java.util.Scanner;

public class BudgetTracker {
    public static Scanner scanner = new Scanner(System.in);
    public static double budget = 0;
    public static void main(String[] args) throws IOException {
        Menu menu = new Menu();
        ExpenseStorage expenseStorage = new ExpenseStorage();
        IncomeStorage incomeStorage = new IncomeStorage();


        int userInput = 0;

        while (true) {

            menu.budgetMenu();
            userInput = Menu.intMenuTryCatch();
            scanner.nextLine();

            switch (userInput) {
                case 1:
                    expenseStorage.createExpense();
                    break;
                case 2:
                    incomeStorage.createIncome();
                    break;
                case 3:
                    expenseStorage.allExpenses();
                    break;
                case 4:
                    incomeStorage.allIncomes();
                    break;
                case 5:
                    expenseStorage.updateExpense();
                    break;
                case 6:
                   incomeStorage.updateIncome();
                    break;
                case 7:
                    expenseStorage.deleteExpense();
                    break;
                case 8:
                    incomeStorage.deleteIncome();
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }
}