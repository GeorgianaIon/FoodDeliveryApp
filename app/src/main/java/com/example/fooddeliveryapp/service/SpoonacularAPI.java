package com.example.fooddeliveryapp.service;

import com.example.fooddeliveryapp.model.Food;
import com.example.fooddeliveryapp.model.FoodResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SpoonacularAPI {
    @GET("recipes/random?apiKey=26661a9d28db4a8bacd57d8133c3a076&number=10")
    Call<FoodResponse> getFoodData();
}
