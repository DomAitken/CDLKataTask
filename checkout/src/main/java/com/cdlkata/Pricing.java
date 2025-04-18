package com.cdlkata;

import java.util.Set;

public class Pricing {
    private final char sku; // Declared all necessary variables
    private final int itemPrice;
    private final int specialQty;
    private final int specialPrice;

    private static final Set<String> SKUs = Set.of("A", "B", "C", "D"); // Set so that other items can be added later if necessary

    public Pricing(char sku, int itemPrice, int specialQty, int specialPrice) {
        this.sku = sku;
        this.itemPrice = itemPrice; // Constructor for initialisation of variables
        this.specialQty = specialQty;
        this.specialPrice = specialPrice;
    }

    public char getSku() {
        if (!SKUs.contains(Character.toUpperCase(sku))) {
            throw new IllegalStateException("Invalid item:" + sku); // Getter accesses the private SKU value and validates it
        }

        return sku;
    }

    public int priceCalc(int itemQuant) {
        if (specialQty > 0 && itemQuant >= specialQty) { // Check if there are any special deals
            int deals = itemQuant / specialQty; // Check the number of complete deals you can get
            int remainingItems = itemQuant % specialQty; // Check the number of remaining items
            int totalPrice =  deals * specialPrice + remainingItems * itemPrice; // Calculate final price

            if (deals > 0) {
                System.out.println("Deal applied for item " + sku + ": " + specialPrice + " for " + specialQty + " (applied " + deals + " time" + (deals > 1 ? "s" : "") + ")"); // Outputs when a special deal has been applied. The "(applied)" section I added after research as it was unclear in the terminal whether or not deals had been added multiple times where necessary
            }

            return totalPrice;
        }

        return itemQuant * itemPrice; // If there's no special deal, return standard price.
    }
}
