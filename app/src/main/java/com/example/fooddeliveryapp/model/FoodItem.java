package com.example.fooddeliveryapp.model;

public class FoodItem {
    String title;
    String image;
    double pricePerServing;

    public Food getFood(){
        return new Food(title, (int)pricePerServing, image);
    }
}
