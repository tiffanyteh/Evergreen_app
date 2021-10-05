package com.example.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

public class SignUpUnitTest {

    @Test
    public void validate_data() {
        Boolean actual = signup.validate("","","");
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void validate_data1() {
        Boolean actual = signup.validate("tiffany","tiffanyteh00@gmail.com","tiffany");
        Boolean expected = false;
        assertEquals(expected, actual);
    }


}
