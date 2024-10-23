package org.example;

import java.io.IOException;
import java.util.Scanner;

public class BudgetTracker {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        Menu menu = new Menu();
        ExpenseStorage expenseStorage = new ExpenseStorage();
        IncomeStorage incomeStorage = new IncomeStorage();
        Budget budget = new Budget(0);


        int userInput = 0;

        while (true) {
            budget.showBudget();

            menu.budgetMenu();
            userInput = Menu.intMenuTryCatch();
            scanner.nextLine();

            switch (userInput) {
                case 1:
                    expenseStorage.createExpense(budget);
                    break;
                case 2:
                    incomeStorage.createIncome(budget);
                    break;
                case 3:
                    expenseStorage.allExpenses();
                    break;
                case 4:
                    incomeStorage.allIncomes();
                    break;
                case 5:
                    expenseStorage.updateExpense(budget);
                    break;
                case 6:
                   incomeStorage.updateIncome(budget);
                    break;
                case 7:
                    expenseStorage.deleteExpense(budget);
                    break;
                case 8:
                    incomeStorage.deleteIncome(budget);
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }
}