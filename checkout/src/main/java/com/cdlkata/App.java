package com.cdlkata;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) { // String[] args allows the terminal to be used for inputs
        Map<Character, Pricing> conditions = new HashMap<>(); // New Hashmap for mapping SKUs onto special prices when right quantities are met
        conditions.put('A', new Pricing('A', 50, 3, 130)); // Entry for each SKU
        conditions.put('B', new Pricing('B', 30, 2, 45));
        conditions.put('C', new Pricing('C', 20, 0, 0));
        conditions.put('D', new Pricing('D', 15, 0, 0));

        Checkout checkout = new Checkout(conditions); // Pricing conditions passed into an instance of the Checkout class
        Scanner scanner = new Scanner(System.in); // Scanner created to monitor inputs

        System.out.println("Enter SKUs to scan (type 'total' to finish):");
        while (true) { // Infinite loop until user types 'total'
            String input = scanner.nextLine().trim(); // .trim() was something I discovered while looking for a way to prevent whitespace

            if (input.isEmpty()) {
                System.out.println("Please enter a valid SKU or type 'total'"); // Added this if statement to separate each input if the user wanted to, making input errors more unlikely
                continue;
            }

            if (input.equalsIgnoreCase("total")) { 
                System.out.println("Final Total: " + checkout.getTotalInPounds());
                break;
            }
            
            try {
                for (char item : input.toCharArray()) {
                    checkout.scanningItem(item); // This will throw if the inputted item isn't in pricing
                }
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
                continue;
            }
        }
        
        scanner.close(); // Scanner closed outside while loop. Was throwing IllegalStateException earlier as it was in the while loop
    }
}
