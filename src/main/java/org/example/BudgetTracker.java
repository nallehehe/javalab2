package org.example;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class BudgetTracker {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        ExpenseStorage expenseStorage = new ExpenseStorage();

        User user = new User("2", "nalle", "hmm");

        //https://howtodoinjava.com/java/date-time/localdate-format-example/
        /*hade massa problem med vad jag antar var serializeringen av localdate objektet
        * så letade runt för andra lösningar och hittade att jag kunde formatera localdate
        * till String istället vilket fungerade för mig!*/
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = LocalDate.now().format(formatter);

        Expense expense = new Expense(300.0, date, user, EExpenseCategory.CLOTHING);

        expenseStorage.saveFile(expense);

//
//        int userInput = 0;

//        while (true) {
//
//            menu.budgetMenu();
//            userInput = scanner.nextInt();
//
//            switch (userInput) {
//                case 1:
//                    //add expenses
//                    break;
//                case 2:
//                    //add income
//                    break;
//                case 3:
//                    //show all expenses
//                    break;
//                case 4:
//                    //show all incomes
//                    break;
//                case 5:
//                    //update an expense
//                    break;
//                case 6:
//                    //update an income
//                    break;
//                case 7:
//                    //delete an expense
//                    break;
//                case 8:
//                    //delete an income
//                    break;
//                case 0:
//                    System.exit(0);
//            }
        //}
    }
}