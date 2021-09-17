package Domain;

import java.io.Serializable;

public class PlantDomain implements Serializable {
    private String title;
    private String pic;
    private String description;
    private Double price;
    private int numberInCard;
    private String height;
    private String width;

    public PlantDomain(){

    }


    public PlantDomain(String title, String pic, String description, Double price, String height, String width) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.price = price;
        this.height = height;
        this.width = width;
    }

    public PlantDomain(String title, String pic, Double price) {
        this.title = title;
        this.pic = pic;
        this.price = price;
    }

    public PlantDomain(String title, String pic, String description, Double price, int numberInCard) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.price = price;
        this.numberInCard = numberInCard;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getNumberInCard() {
        return numberInCard;
    }

    public void setNumberInCard(int numberInCard) {
        this.numberInCard = numberInCard;
    }
}
