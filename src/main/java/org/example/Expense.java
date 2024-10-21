package org.example;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import static org.example.BudgetTracker.scanner;

public class Expense extends Transaction{
    private EExpenseCategory expenseCategory;

    public Expense(int id, double amount, String date, User user, EExpenseCategory expenseCategory) {
        super(id, amount, date, user);
        this.expenseCategory = expenseCategory;
    }

    //https://www.javatpoint.com/java-collections-max-method
    //https://howtodoinjava.com/java/date-time/localdate-format-example/
    /*en metod som skapar expense objekt för att sen serializeras till ett json objekt
    * readfile som kollar att listan är up to date
    * hade massa problem med vad jag antar var serializeringen av localdate objektet
    * så letade runt för andra lösningar och hittade att jag kunde formatera localdate
    * till String istället vilket fungerade för mig
    *
    * sen en collections som kollar vad det största idt är i min json-fil och en int som ger det ett +1 så även om programmet stängs ner
    * så kommer den läsa igenom filen för att se vad det största värdet faktiskt är och aldrig skriva över det!
    *
    * den är halvt ai genererad, testade väldigt mycket med automaticinteger som inte tog mig så jättelångt
    * vet inte om jag använde den fel eller om det bara inte funkade just i detta scenariot när man ska läsa in i en fil och kolla deras ids*/
    public static void createExpense(ExpenseStorage expenseStorage) throws IOException {
        expenseStorage.readFile();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = LocalDate.now().format(formatter);

        System.out.println("First name: ");
        String firstName = scanner.nextLine();

        System.out.println("Last name: ");
        String lastName = scanner.nextLine();

        System.out.println("How much did it cost?");
        double amount = scanner.nextDouble();

        User user = new User(firstName, lastName);

        int maxId = expenseStorage.getExpenseList().isEmpty() ? 0 : Collections.max(expenseStorage.getExpenseList().keySet());
        int newId = maxId + 1;

        Expense expense = new Expense(newId, amount, date, user, EExpenseCategory.CLOTHING);

        expenseStorage.addExpense(expense);

        System.out.println("Create expense " + expense.getId());
    }

    /*ser precis ut som min createexpense metod men istället för att använda mig ut av collections.max
    * för att hitta det största numret och ge det newId för att aldrig skriva över någon
    * så har jag en scanner för id där man skriver in själv id:t på json objektet man vill uppdatera
    * och skriver om alla olika parametrar och skapad igen och skriver över!*/
    public static void updateExpense(ExpenseStorage expenseStorage) throws IOException {
        expenseStorage.readFile();

        System.out.println("What expense would you like to update?");
        int id = scanner.nextInt();
        scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = LocalDate.now().format(formatter);

        System.out.println("First name: ");
        String firstName = scanner.nextLine();

        System.out.println("Last name: ");
        String lastName = scanner.nextLine();

        System.out.println("How much did it cost?");
        double amount = scanner.nextDouble();

        User user = new User(firstName, lastName);

        Expense expense = new Expense(id, amount, date, user, EExpenseCategory.CLOTHING);

        expenseStorage.addExpense(expense);

        System.out.println("update expense " + expense.getId());
    }
}
