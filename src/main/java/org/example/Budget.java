package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class Budget {
    private String fileName = "src/main/budget.json";
    private double budget;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /*min budget klass som har med min budget.json att göra!
    * ville ha ett sätt att ha en budget som är sparad på samma sätt som min expense och income istället för att
    * ha ett hårdkodat värde i BudgetTracker så även när programmet stängs ner så
    * när man startar det igen så är budgetten exakt likadan som man lämnade den */

    public Budget(double budget) {
        this.budget = budget;
    }

    public double getBudget() {
        return budget;
    }

    public void readBudget() throws IOException {
        File file = new File(fileName);

        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                Reader reader = new FileReader(file);

                budget = gson.fromJson(reader, double.class);
            }
        } catch (NullPointerException e) {
        }
    }

    public void saveBudget() throws IOException {
        FileWriter fw = new FileWriter(new File(fileName));

        gson.toJson(budget, fw);

        fw.close();
    }

    public void addToBudget(double amount) throws IOException {
        readBudget();
        budget += amount;
        saveBudget();
    }

    public void deductFromBudget(double amount) throws IOException {
        readBudget();
        budget -= amount;
        saveBudget();
    }

    public void showBudget() throws IOException {
        readBudget();
        System.out.println("\nCurrent budget: " + budget + " kr");
    }
}
