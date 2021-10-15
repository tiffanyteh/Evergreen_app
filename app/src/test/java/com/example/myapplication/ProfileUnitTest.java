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

    @Test
    public void getAddress(){
        String actualaddress = "Desa Airmas";
        String expectedaddress = actualaddress;
        assertEquals(expectedaddress,actualaddress);
    }

    @Test
    public void getUsername(){
        String actualusername = "Tiffany";
        String expectedusername = actualusername;
        assertEquals(expectedusername,actualusername);
    }

    @Test
    public void getPhoneNo(){
        String actualphone = "018-4001349";
        String expectedphone = actualphone;
        assertEquals(expectedphone,actualphone);
    }

    @Test
    public void getWishlistTitle(){
        String actualtitle = "acacia";
        String expectedtitle = actualtitle;
        assertEquals(expectedtitle,actualtitle);
    }

    @Test
    public void getWishlistPrice(){
        String actualprice = "RM10.10";
        String expectedprice = actualprice;
        assertEquals(expectedprice,actualprice);
    }

    @Test
    public void getOrderID(){
        String actualID = "A0109TYB45667";
        String expectedID = actualID;
        assertEquals(expectedID,actualID);
    }

    @Test
    public void getOrderTotal(){
        String actualtotal = "RM50.00";
        String expectedtotal = actualtotal;
        assertEquals(expectedtotal,actualtotal);
    }


    @Test
    public void getOrderDate(){
        String actualdate = "10/04/2021 5:45pm";
        String expecteddate = actualdate;
        assertEquals(expecteddate,actualdate);
    }

    @Test
    public void getOrderItem(){
        String actualtitle = "acacia";
        String expectedtitle = actualtitle;
        assertEquals(expectedtitle,actualtitle);
    }

    @Test
    public void getOrderQty(){
        String actualqty = "1";
        String expectedqty = actualqty;
        assertEquals(expectedqty,actualqty);
    }

    @Test
    public void getOrderPrice(){
        String actualprice = "acacia";
        String expectedprice = actualprice;
        assertEquals(expectedprice,actualprice);
    }
}
