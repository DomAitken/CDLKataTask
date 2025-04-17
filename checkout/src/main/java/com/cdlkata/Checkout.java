package com.cdlkata;

import java.util.*;

public class Checkout {
    private final Map<Character, Pricing> pricing; // Links items A and B to their special pricing conditions
    private final Map<Character, Integer> itemNum; // Tracks the number of each item scanned

    public Checkout(Map<Character, Pricing> pricing) {
        this.pricing = pricing; // Constructor initialises the pricing conditions and the itemNum variable
        this.itemNum = new HashMap<>();
    }

}
