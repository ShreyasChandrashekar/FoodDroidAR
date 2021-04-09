package com.example.fdroid;

public class FoodData {
    String Name,Price,AR;

    public String getAR() {
        return AR;
    }

    public void setAR(String ar) {
        AR = ar;
    }

    public FoodData(String name, String price, String ar) {
        Name = name;
        Price = price;
        AR = ar;
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
