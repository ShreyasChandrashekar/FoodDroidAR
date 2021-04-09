package com.example.fdroid;

public class FoodData {
    String Name,Price;

    public FoodData(String name, String price) {
        Name = name;
        Price = price;
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
}
