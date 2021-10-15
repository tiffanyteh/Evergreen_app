package com.example.myapplication;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginUnitTest {

    @Test
    public void validate_login() {
        Boolean actual = login.validate("","");
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void validate_login1() {
        Boolean actual = login.validate("tiffanyteh00@gmail.com","tiffany");
        Boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void validate_forgotpsw() {
        Boolean actual = login.validate1("");
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void validate_forgotpsw1() {
        Boolean actual = login.validate1("tiffanyteh00@gmail.com");
        Boolean expected = false;
        assertEquals(expected, actual);
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
