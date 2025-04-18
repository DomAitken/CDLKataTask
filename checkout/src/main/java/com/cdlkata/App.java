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
            if (input.equalsIgnoreCase("total")) { // Checks to see if the user input is 'total' and begins to tally the price immediately if so 
                System.out.println("Final Total: " + checkout.getTotalInPounds());
                break;
            } else if (!input.matches("[a-dA-D]+") && !input.equalsIgnoreCase("total")) { // Makes sure the input is either an SKU or 'total', otherwise the input is ruled invalid
                System.out.println("Invalid input");
            }
        }

        scanner.close(); // Scanner closed
    }
}
