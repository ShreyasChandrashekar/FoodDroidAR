package com.example.fdroid;

public class FoodData {
    String Name,Price,AR,Image;



    public FoodData(String name, String price, String ar,String image) {
        Name = name;
        Price = price;
        AR = ar;
        Image = image;
    }

    public FoodData() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getAR() {
        return AR;
    }

    public void setAR(String ar) {
        AR = ar;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
