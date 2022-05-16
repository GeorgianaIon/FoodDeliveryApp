package com.example.fooddeliveryapp.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fooddeliveryapp.service.ServiceGenerator;
import com.example.fooddeliveryapp.service.SpoonacularAPI;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class FoodRepository {

    private  MutableLiveData<List<Food>> selectedFood;
    private MutableLiveData<List<Food>> foodList;
    private static FoodRepository instance;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private MutableLiveData<Integer> totalPrice;

    public FoodRepository(){
        selectedFood = new MutableLiveData<>();
        List<Food> newList = new ArrayList<>();
        selectedFood.setValue(newList);
        foodList = new MutableLiveData<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
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
    }

//    public void insert(Food food) {
//        List<Food> currentFood = selectedFood.getValue();
//        for(Food food1 : currentFood)
//        {
//            if(food1.equals(food)){
//                food1.setAmount(food1.getAmount()+1);
//            }
//
//        }
//        currentFood.add(food);
//        selectedFood.setValue(currentFood);
////        calculateCartValue();
//    }
//
//    public void deleteFood(Food food) {
//        List<Food> currentFood = selectedFood.getValue();
//        for(Food food1 : currentFood)
//        {
//            if(food1.equals(food)){
//                food1.setAmount(food1.getAmount()+1);
//            }
//        }
//        currentFood.remove(food);
//        selectedFood.setValue(currentFood);
////        calculateCartValue();
//    }

    private void calculateCartValue(){
        if(selectedFood.getValue() == null) return;
        int total = 0;
        List<Food> cartItems = selectedFood.getValue();
        for(Food food : cartItems){
            total += food.getPrice() * food.getAmount();
        }
        totalPrice.setValue(total);
    }

    public LiveData<Integer> getTotalPrice() {
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
