package com.cdlkata;

import java.util.*;

public class Checkout {
    private final Map<Character, Pricing> pricing; // Links items A and B to their special pricing conditions
    private final Map<Character, Integer> itemNum; // Tracks the number of each item scanned

    public Checkout(Map<Character, Pricing> pricing) {
        this.pricing = pricing; // Constructor initialises the pricing conditions and the itemNum variable
        this.itemNum = new HashMap<>();
    }

    public void scanningItem(char item) { // Method for processing individually scanned items
        item = Character.toUpperCase(item);
        if (!pricing.containsKey(item)) {
            throw new IllegalArgumentException("Unknown item: " + item); // An item that isn't in the pricing map will throw an illegal argument exception and be flagged as unknown
        }
        itemNum.put(item, itemNum.getOrDefault(item, 0) + 1); // Add the item to the basket, using the .put method in order to edit the map
        System.out.println("Scanned: " + item);
        System.out.println("Running Total: " + getTotalInPounds());
    }

    public String getTotalInPounds() {
        int total = 0; // Set the total price to 0
        for (Map.Entry<Character, Integer> entry : itemNum.entrySet()) { // Loop through the itemNum map
            Pricing condition = pricing.get(entry.getKey()); // Get the pricing conditions for the item
            total += condition.priceCalc(entry.getValue()); // Add the calculated price for the quantity of the item
        }
        return String.format("£%.2f", total / 100.0); // Convert the total from pence to pounds and return the formatted string
    }  // Formatter specifies a floating point number with letter f, .2 details 2 decimal places
}
