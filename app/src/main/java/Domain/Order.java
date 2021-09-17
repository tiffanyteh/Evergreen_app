package Domain;

import java.io.Serializable;

public class Order implements Serializable {
    String name;
    String title;
    Double total;
    String amount;
    String price;
    String address;
    String phoneno;
    String orderid;

    public Order(String name, String title, Double total, String amount, String price, String address, String phoneno, String orderid) {
        this.name = name;
        this.title = title;
        this.total = total;
        this.amount = amount;
        this.price = price;
        this.address = address;
        this.phoneno = phoneno;
        this.orderid = orderid;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
}
