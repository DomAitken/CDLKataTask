package com.cdlkata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

public class CheckoutTests {

    private Checkout checkout;

    @BeforeEach
    public void setup() {
        Map<Character, Pricing> conditions = Map.of(
            'A', new Pricing('A', 50, 3, 130),
            'B', new Pricing('B', 30, 2, 45),
            'C', new Pricing('C', 20, 0, 0),
            'D', new Pricing('D', 15, 0, 0)
        );
        checkout = new Checkout(conditions);
    }

    @Test
    public void testSingleItems() { // Checks single item gets scanned correctly
        checkout.scanningItem('A');
        assertEquals("£0.50", checkout.getTotalInPounds());
        checkout.scanningItem('B');
        assertEquals("£0.80", checkout.getTotalInPounds()); // Checking to see if the running total is working
    }

    @Test
    public void testSpecialPriceForA() { // Checks to ensure that 3 As comes out as 130
        checkout.scanningItem('A');
        checkout.scanningItem('A');
        checkout.scanningItem('A');
        assertEquals("£1.30", checkout.getTotalInPounds());
    }

    @Test
    public void testSpecialPriceForB() { // Checks to see if 2 Bs comes out as 45
        checkout.scanningItem('B');
        checkout.scanningItem('B'); 
        assertEquals("£0.45", checkout.getTotalInPounds());
    }

    @Test
    public void testMixOfItems() { // Checks to ensure that a diverse user input can be successfully processed and tallied
        checkout.scanningItem('A');
        checkout.scanningItem('B');
        checkout.scanningItem('A');
        checkout.scanningItem('D');
        checkout.scanningItem('B'); 
        checkout.scanningItem('A');
        checkout.scanningItem('C');
        checkout.scanningItem('D'); 
        assertEquals("£2.25", checkout.getTotalInPounds());
    }

    @Test
    public void testInvalidSkuThrowsException() { // Checks that an invalid input is caught and the user is told of their mistake
        Exception exception = assertThrows(IllegalArgumentException.class, () -> checkout.scanningItem('Z'));
        assertTrue(exception.getMessage().contains("Unknown item: Z"));
    }
}
