package Domain;

import java.io.Serializable;

public class CartItem implements Serializable {
    private String name;
    private Double price;
    private int amount;
    private String pic;

    public CartItem(String name, Double price, int amount, String pic) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
