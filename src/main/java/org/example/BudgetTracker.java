package org.example;

import java.util.Scanner;

public class BudgetTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();

        int userInput = 0;

        while (true) {

            menu.budgetMenu();
            userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    //track budget
                    break;
                case 2:
                    //add expenses
                    break;
                case 3:
                    //add income
                    break;
                case 4:
                    //show all expenses
                    break;
                case 5:
                    //show all incomes
                    break;
                case 6:
                    //update an expense
                    break;
                case 7:
                    //update an income
                    break;
                case 8:
                    //delete an expense
                    break;
                case 9:
                    //delete an income
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }
}