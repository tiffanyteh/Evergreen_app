package com.example.myapplication;
import org.junit.Test;

import static org.junit.Assert.*;


public class ProfileUnitTest {

    @Test
    public void validate_phoneno() {
        Boolean actual = editProfile.validate("018-4001349","016-4073898");
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void validate_phoneno1() {
        Boolean actual = editProfile.validate("018-4001349", "018-4001349");
        Boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void validate_name() {
        Boolean actual = editProfile.validate1("Tiffany","Tiffany00");
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void validate_name1() {
        Boolean actual = editProfile.validate1("Tiffany", "Tiffany");
        Boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void validate_address() {
        Boolean actual = editProfile.validate2("Desa Airmas","Taman Meranti");
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void validate_address1() {
        Boolean actual = editProfile.validate2("Desa Airmas", "Desa Airmas");
        Boolean expected = false;
        assertEquals(expected, actual);
    }

}
