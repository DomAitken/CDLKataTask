package com.cdlkata;

import java.util.Set;

public class Pricing {
    private final char sku; // Declared all necessary variables
    private final int itemPrice;
    private final int dealsQuant;
    private final int specialPrice;

    private static final Set<String> SKUs = Set.of("A", "B", "C", "D"); // Set so that other items can be added later if necessary

    public Pricing(char sku, int unitPrice, int specialQty, int specialPrice) {
        this.sku = sku;
        this.itemPrice = unitPrice; // Constructor for initialisation of variables
        this.dealsQuant = specialQty;
        this.specialPrice = specialPrice;
    }

    public char getSku() {
        if (!SKUs.contains(Character.toUpperCase(sku))) {
            throw new IllegalStateException("Invalid item:" + sku); // Getter accesses the private SKU value and validates it
        }

        return sku;
    }

    public int priceCalc(int itemQuant) {
        if (dealsQuant > 0) { // Check if there are any special deals
            int deals = itemQuant / dealsQuant; // Check the number of complete deals you can get
            int remainingItems = itemQuant % dealsQuant; // Check the number of remaining items
            return deals * specialPrice + remainingItems * itemPrice; // Calculate final price
        }

        return itemQuant * itemPrice; // If there's no special deal, return standard price.
    }
}
