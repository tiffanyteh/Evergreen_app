package com.example.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

public class CartUnitTest {

    @Test
    public void validate_address() {
        Boolean actual = Cart.validate("");
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void validate_address1() {
        Boolean actual = Cart.validate("Desa Air Mas");
        Boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void validate_item() {
        Boolean actual = Cart.validate1("Total Price: RM0.00");
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void validate_item1() {
        Boolean actual = Cart.validate1("Total Price: RM10.00");
        Boolean expected = false;
        assertEquals(expected, actual);
    }
}
