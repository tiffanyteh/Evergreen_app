package com.example.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

public class HomeUnitTest {

    @Test
    public void validate_keyword() {
        Boolean actual = home.validate("");
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void validate_keyword1() {
        Boolean actual = home.validate("acacia");
        Boolean expected = false;
        assertEquals(expected, actual);
    }
}
