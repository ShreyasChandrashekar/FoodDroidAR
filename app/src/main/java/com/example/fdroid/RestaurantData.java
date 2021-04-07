package com.example.fdroid;

public class RestaurantData {
    String Name;
    String Category;
    String Area;

    public RestaurantData() {
    }

    public RestaurantData(String name, String category, String area) {
        Name = name;
        Category = category;
        Area = area;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }
}
