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
    /*fixade nu så om en json fil inte existerar så kraschar inte programmet längre men istället skapar en man kan spara till!
    *
    * en metod som deserializerar json-filen till en hashmap så alla mina metoder kan läsa och nå innehållet i den*/
    public void readFile() throws IOException {
        File file = new File(fileName);

        if (!file.exists()) {
            file.createNewFile();

            //https://stackoverflow.com/questions/218384/what-is-a-nullpointerexception-and-how-do-i-fix-it
            /*testade hela programmet med att ta bort alla json-filer och alla olika val som funkade tidigare
            * men nu när jag skapade min budget klass och gav alla mina metoder Budget budget parametrar så kunde koden
            * inte kompilera och krashade eftersom key-värdet är null*/
            if (expenseList == null) {
                System.out.println("Expense list is empty");
            }

        } else {
            Type type = new TypeToken<Map<Integer, Expense>>() {}.getType();

            Reader reader = new FileReader(fileName);

            expenseList = gson.fromJson(reader, type);
        }
    }

    /*en metod som sparar och serializerar hashmappen till en json*/
    public void saveFile() throws IOException {
        FileWriter fw = new FileWriter(new File(fileName));

        gson.toJson(expenseList, fw);

        fw.close();
    }

    //https://www.javatpoint.com/java-collections-max-method
    //https://howtodoinjava.com/java/date-time/localdate-format-example/
    /*en metod som skapar expense objekt för att sen serializeras till ett json objekt
     * hade massa problem med vad jag antar var serializeringen av localdate objektet
     * så letade runt för andra lösningar och hittade att jag kunde formatera localdate
     * till String istället vilket fungerade mycket bättre
     *
     * sen en collections.max som kollar vad det största idt är i min hashmap efter jag deserializerat min json-fil med min readFile() metod
     * sen med det hämtade id:t så plussar jag på med +1 för att alltid öka id:t med ett så det håller sig unikt på det sättet
     *
     * den är ai genererad, testade väldigt mycket med atomicinteger och en count++ i min user klass (när jag sparade
     * id:t där) men det funkade tyvärr inte så bra*/
    public void createExpense(Budget budget) throws IOException {
        readFile();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = LocalDate.now().format(formatter);

        System.out.println("First name: ");
        String firstName = scanner.nextLine();

        System.out.println("Last name: ");
        String lastName = scanner.nextLine();

        System.out.println("How much did it cost?");
        double amount = Menu.doubleTryCatch();

        budget.deductFromBudget(amount);

        System.out.println("All expense categories: ");
        for (EExpenseCategory category : EExpenseCategory.values()) {
            System.out.println(category.getExpenseCategory() + ". " + category.name());
        }

        System.out.println("What category?");
        int category = Menu.categoryTryCatch(EExpenseCategory.values().length);

        EExpenseCategory selectedCategory = EExpenseCategory.values()[category - 1];

        User user = new User(firstName, lastName);

            int maxId = expenseList.isEmpty() ? 0 : Collections.max(expenseList.keySet());
            int newId = maxId + 1;

        Expense expense = new Expense(newId, amount, date, user, selectedCategory);

        addExpense(expense);

        System.out.println("Expense with the id " + expense.getId() + " has been created");
    }

    /*ser precis ut som min createexpense metod men istället för att använda mig ut av collections.max
     * för att hitta det största numret så har jag en scanner för id där man skriver in själv id:t på json objektet man vill uppdatera
     * och skriver om alla olika parametrar och skapad igen och skriver över!
     *
     * hämtar även det tidigare satta amount från id:t som skrivs för att ta bort den från budgetten
     * och samtidigt plussa på den uppdaterade amount istället*/
    public void updateExpense(Budget budget) throws IOException {
        readFile();

        System.out.println("What expense would you like to update?");
        int id = Menu.intTryCatch();
        scanner.nextLine();

        if (!expenseList.containsKey(id)) {
            System.out.println("An expense with that id does not exist.");
            
        } else {
            Expense getExpenseAmount = expenseList.get(id);
            double oldAmount = getExpenseAmount.getAmount();

            System.out.println("First name: ");
            String firstName = scanner.nextLine();

            System.out.println("Last name: ");
            String lastName = scanner.nextLine();

            System.out.println("How much did it cost?");
            double amount = Menu.doubleTryCatch();

            budget.deductFromBudget(oldAmount);
            budget.addToBudget(amount);

            System.out.println("All expense categories: ");
            for (EExpenseCategory category : EExpenseCategory.values()) {
                System.out.println(category.getExpenseCategory() + ". " + category.name());
            }

            System.out.println("What category: ");
            int category = Menu.categoryTryCatch(EExpenseCategory.values().length);

            EExpenseCategory selectedCategory = EExpenseCategory.values()[category - 1];

            User user = new User(firstName, lastName);

            //hämtar utgiftens tidigare datum så det inte skrivs över när man uppdaterar en utgift
            String date = expenseList.get(id).getDate();

            Expense expense = new Expense(id, amount, date, user, selectedCategory);

            addExpense(expense);

            System.out.println("Expense with the id " + expense.getId() + " has been updated");
        }
    }

    /*deserializerar json-objektet för att ha tillgång till alla id:s och tar då bort det från hashmappen om det
    * inskrivna id:t är giltigt, om det inskrivna id:t inte finns så skrivs det ut! */
    public void deleteExpense(Budget budget) throws IOException {
        readFile();

        System.out.println("What expense would you like to delete?");
        int id = Menu.intTryCatch();
        scanner.nextLine();

        if (!expenseList.containsKey(id)) {
            System.out.println("That id does not exist");

        } else {
            Expense expense = expenseList.get(id);
            expenseList.remove(id);
            budget.addToBudget(expense.getAmount());

            System.out.println("Expense with the id " + id + " has been deleted.");

            saveFile();
        }
    }

    //for each som går igenom alla json-objekt i expense.json
    public void allExpenses() throws IOException {
        readFile();

        if (expenseList.isEmpty()) {
            System.out.println("There are no expenses check");
        } else {
            for(int id  : expenseList.keySet()) {
                Expense expense = expenseList.get(id);
                System.out.println("\n----------------------");
                System.out.println("Id: " + id );
                System.out.println("First name: " + expense.getUser().getFirstName());
                System.out.println("Last name: " + expense.getUser().getLastName());
                System.out.println("Amount: " + expense.getAmount());
                System.out.println("Date: " + expense.getDate());
                System.out.println("Category: " + expense.getExpenseCategory());
            }
        }
    }

    /*en metod som används i create, update och delete i slutet för att då spara till hashmappen och
    * hädanefter använda sig utav saveFile() metod för att serializera det till ett json-objekt och spara det*/
    public void addExpense(Expense expense) throws IOException {
        expenseList.put(expense.getId(), expense);
        saveFile();
    }
}
