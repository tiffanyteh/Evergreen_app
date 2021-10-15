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

    @Test
    public void getUsername(){
        String actualname = "Tiffany";
        String expectedname = actualname;
        assertEquals(expectedname,actualname);
    }

    @Test
    public void getEmail(){
        String actualemail = "Tiffanyteh00@gmail.com";
        String expectedemail = actualemail;
        assertEquals(expectedemail,actualemail);
    }

    @Test
    public void getPassword(){
        String actualpsw = "Tiffany";
        String expectedpsw = actualpsw;
        assertEquals(expectedpsw,actualpsw);
    }

}
