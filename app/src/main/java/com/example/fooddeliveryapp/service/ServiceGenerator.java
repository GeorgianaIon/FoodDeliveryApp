package com.example.fooddeliveryapp.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    public static SpoonacularAPI spoonacularApi;

    public static SpoonacularAPI getSpoonacularApi(){
        if(spoonacularApi == null) {
            spoonacularApi = new Retrofit.Builder().baseUrl("https://api.spoonacular.com/").
                    addConverterFactory(GsonConverterFactory.create()).
                    build().create(SpoonacularAPI.class);
        }
        return spoonacularApi;
    }
}
