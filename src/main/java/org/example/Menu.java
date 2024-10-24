package org.example;

import java.util.InputMismatchException;

import static org.example.BudgetTracker.scanner;

public class Menu {
    public void budgetMenu() {
        System.out.println("\nBudget App");
        System.out.println("------------------------");
        System.out.println("[1] - Add an expense");
        System.out.println("[2] - Add an income");
        System.out.println("[3] - Check all expenses");
        System.out.println("[4] - Check all incomes");
        System.out.println("[5] - Update an expense");
        System.out.println("[6] - Update an income");
        System.out.println("[7] - Delete an expense");
        System.out.println("[8] - Delete an income");
        System.out.println("[0] - Exit");
        System.out.print("Choose an option: \n");
    }

    public static int intMenuTryCatch() {
        int input = 0;

        while (true) {
            try {
                input = scanner.nextInt();

                if (input < 0 || input > 8) {
                    System.out.println("Please enter a number between 0 and 8.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, only numbers are allowed here.");
                scanner.next();
            }
        }

        return input;
    }

    public static int intTryCatch() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Not a valid input. Enter numbers only.");
                scanner.next();
            }
        }
    }

    //kan inte skriva in negativa nummer längre!
    public static double doubleTryCatch() {
        while (true) {
            try {
                double input = scanner.nextDouble();
                if (input < 0) {
                    System.out.println("Negative numbers are not allowed, try again!");
                } else {
                    return input;
                }
            }
            catch (Exception e) {
                System.out.println("Please only enter numbers here.");
                scanner.next();
            }
        }
    }

    public static int categoryTryCatch(int size) {
        int input = -1;
        while(true) {
            try {
                input = scanner.nextInt();
                if (input <= 0 || input > size) {
                    System.out.println("Not a valid number.");
                } else {
                    break;
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input, only numbers are allowed here.");
                scanner.next();
            }
        }
        return input;
    }


    /*public static String stringTryCatch() {
        while (true) {
            try {
                if (scanner.nextLine().matches("[a-zA-Z]+}")) {
                    return scanner.nextLine();
                }
            } catch (Exception e) {
                System.out.println("Not a valid input. Try again!");
            }
        }
    }*/
}
