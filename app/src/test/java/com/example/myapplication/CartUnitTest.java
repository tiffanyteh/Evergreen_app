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
    public void getQuantity(){
        String actualqty = "2";
        String expectedqty = actualqty;
        assertEquals(expectedqty,actualqty);
    }

    @Test
    public void getTotal(){
        String actualtotal = "RM50.00";
        String expectedtotal = actualtotal;
        assertEquals(expectedtotal,actualtotal);
    }

    @Test
    public void getAddress(){
        String actualaddress = "Desa Airmas";
        String expectedaddress = actualaddress;
        assertEquals(expectedaddress,actualaddress);
    }
}
