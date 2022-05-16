package com.example.fooddeliveryapp.model;

import java.util.ArrayList;
import java.util.List;

public class FoodResponse {

    private List<FoodItem> recipes;

    public FoodResponse(){
        recipes = new ArrayList<>();
    }

    public List<FoodItem> getRecipes2(){
        return recipes;
    }

    public void setRecipes(List<FoodItem> recipes) {
        this.recipes = recipes;
    }

    public List<Food> getRecipes() {
        List<Food> output = new ArrayList<>();
        for(FoodItem foodItem : recipes){
            output.add(foodItem.getFood());
        }
        return output;
    }
}
