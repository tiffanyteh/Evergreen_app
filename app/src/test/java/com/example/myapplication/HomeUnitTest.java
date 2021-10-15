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

    @Test
    public void getkeyword(){
        String actualkeyword = "acacia";
        String expectedkeyword = actualkeyword;
        assertEquals(expectedkeyword,actualkeyword);
    }

    @Test
    public void getTitle(){
        String actualtitle = "Acacia";
        String expectedtitle = actualtitle;
        assertEquals(expectedtitle,actualtitle);
    }

    @Test
    public void getPrice(){
        String actualprice = "RM12.12";
        String expectedprice = actualprice;
        assertEquals(expectedprice,actualprice);
    }

    @Test
    public void getDescription(){
        String actualdes = "Acacia is a tree";
        String expecteddes = actualdes;
        assertEquals(expecteddes,actualdes);
    }

}
