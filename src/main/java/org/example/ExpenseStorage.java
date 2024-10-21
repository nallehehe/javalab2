package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.example.BudgetTracker.scanner;

public class ExpenseStorage {
    private Map<Integer, Expense> expenseList = new HashMap<>();
    private String fileName = "src/main/expense.json";
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ExpenseStorage() {
    }


    //https://stackoverflow.com/questions/33288035/filereader-create-file-if-does-not-exist
    /*fixade nu så om en json fil inte existerar så kraschar inte programmet längre men istället skapar en man kan spara till!*/
    public void readFile() throws IOException {
        File file = new File(fileName);

        if (!file.exists()) {
            file.createNewFile();
        } else {
            Type type = new TypeToken<Map<Integer, Expense>>() {}.getType();

            Reader reader = new FileReader(fileName);

            expenseList = gson.fromJson(reader, type);
        }
    }

    /*instansierar readFile metoden här för att kolla om json-filen redan har existerande data och om id:t är unikt så sparas ett nytt json objekt ner
    * om id:t inte är unikt så skrivs det över! */
    public void saveFile() throws IOException {
        FileWriter fw = new FileWriter(new File(fileName));

        gson.toJson(expenseList, fw);

        fw.close();
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
    public void createExpense() throws IOException {
        readFile();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = LocalDate.now().format(formatter);

        System.out.println("First name: ");
        String firstName = scanner.nextLine();

        System.out.println("Last name: ");
        String lastName = scanner.nextLine();

        System.out.println("How much did it cost?");
        double amount = scanner.nextDouble();

        User user = new User(firstName, lastName);

        int maxId = expenseList.isEmpty() ? 0 : Collections.max(expenseList.keySet());
        int newId = maxId + 1;

        Expense expense = new Expense(newId, amount, date, user, EExpenseCategory.CLOTHING);

        addExpense(expense);

        System.out.println("Expense with the id " + expense.getId() + " has been created");
    }

    /*ser precis ut som min createexpense metod men istället för att använda mig ut av collections.max
     * för att hitta det största numret och ge det newId för att aldrig skriva över någon
     * så har jag en scanner för id där man skriver in själv id:t på json objektet man vill uppdatera
     * och skriver om alla olika parametrar och skapad igen och skriver över!*/
    public void updateExpense() throws IOException {
        readFile();

        System.out.println("What expense would you like to update?");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("First name: ");
        String firstName = scanner.nextLine();

        System.out.println("Last name: ");
        String lastName = scanner.nextLine();

        System.out.println("How much did it cost?");
        double amount = scanner.nextDouble();

        User user = new User(firstName, lastName);

        String date = expenseList.get(id).getDate();

        Expense expense = new Expense(id, amount, date, user, EExpenseCategory.CLOTHING);

        addExpense(expense);

        System.out.println("Expense with the id " + expense.getId() + " has been updated");
    }

    /*instansierar readFile metoden här för att kolla om json-filen redan har existerande data och om id:t är unikt så sparas ett nytt json objekt ner
     * om id:t inte är unikt så skrivs det över! */
    public void deleteExpense() throws IOException {
        readFile();
        System.out.println("What expense would you like to delete?");
        int id = scanner.nextInt();
        scanner.nextLine();

        expenseList.remove(id);

        System.out.println("Expense with the id " + id + " has been deleted.");

        saveFile();
    }

    public void addExpense(Expense expense) throws IOException {
        expenseList.put(expense.getId(), expense);
        saveFile();
    }
}
