package com.example.fooddeliveryapp.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fooddeliveryapp.model.Food;
import com.example.fooddeliveryapp.model.FoodResponse;
import com.example.fooddeliveryapp.service.ServiceGenerator;
import com.example.fooddeliveryapp.service.SpoonacularAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodRepository {

    private  MutableLiveData<List<Food>> selectedFood;
    private MutableLiveData<List<Food>> foodList;
    private static FoodRepository instance;
    private MutableLiveData<Integer> totalCart;
    private MutableLiveData<Integer> totalPrice;

    public FoodRepository(){
        selectedFood = new MutableLiveData<>();
        List<Food> newList = new ArrayList<>();
        selectedFood.setValue(newList);
        foodList = new MutableLiveData<>();
        totalCart = new MutableLiveData<>();
        totalPrice = new MutableLiveData<>();
     }


    public LiveData<List<Food>> getFoodList() {
        return foodList;
    }

    public static FoodRepository getInstance(){
        if(instance==null)
            instance=new FoodRepository();
        return instance;
    }

    public LiveData<List<Food>> getSelectedFood() {
        return selectedFood;
    }

    public void setFoodList(List<Food> foodList) {
        selectedFood.setValue(foodList);
        calculateCartValue();
        calculateTotal();
    }

    private void calculateCartValue(){
        if(selectedFood.getValue() == null) return;
        int total = 0;
        List<Food> cartItems = selectedFood.getValue();
        for(Food food : cartItems){
            total += food.getPrice() * food.getAmount();
        }
        totalCart.setValue(total);
    }

    public LiveData<Integer> getTotalPrice() {
        if(totalCart.getValue() == null){
            totalCart.setValue(0);
        }
        return totalCart;
    }

    private void calculateTotal(){
        if(selectedFood.getValue() == null) return;
        int total = totalCart.getValue() + 25;
        totalPrice.setValue(total);
    }

    public LiveData<Integer> getTotal() {
        if(totalPrice.getValue() == null){
            totalPrice.setValue(0);
        }
        return totalPrice;
    }

    public void retrieveFoodList(){
        SpoonacularAPI spoonacularAPI = ServiceGenerator.getSpoonacularApi();
        Call<FoodResponse> call = spoonacularAPI.getFoodData();
        call.enqueue(new Callback<FoodResponse>() {
            @Override
            public void onResponse(@NonNull Call<FoodResponse> call, @NonNull Response<FoodResponse> response) {
                if(response.isSuccessful()){
                    List<Food> list = foodList.getValue();
                    if(list == null){
                        foodList.setValue(response.body().getRecipes());
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<FoodResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :( "+ t.getMessage());
            }
        });
    }
}
