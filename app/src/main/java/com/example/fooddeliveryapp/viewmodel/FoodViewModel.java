package com.example.fooddeliveryapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fooddeliveryapp.repository.AppRepository;
import com.example.fooddeliveryapp.model.Food;
import com.example.fooddeliveryapp.repository.FoodRepository;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class FoodViewModel extends AndroidViewModel {
    private AppRepository appRepository;
    private MutableLiveData<FirebaseUser> user;
    private MutableLiveData<Boolean> loggedOut;
    private FoodRepository repository;

    public FoodViewModel(@NonNull Application application) {
        super(application);

        appRepository = new AppRepository(application);
        user = appRepository.getUser();
        loggedOut = appRepository.getLoggedOut();
        repository = FoodRepository.getInstance();
    }

    public void logOut(){
        appRepository.logOut();
    }

    public MutableLiveData<Boolean> getLoggedOut() {
        return loggedOut;
    }

    public MutableLiveData<FirebaseUser> getUser() {
        return user;
    }

    public LiveData<List<Food>> getFood(){
        return repository.getFoodList();
    }

    public void addToCart(List<Food> foods){
        List<Food> foodss = new ArrayList<>();
        for(Food food : foods){
            if(food.getAmount() > 0){
                foodss.add(food);
            }
        }
        repository.setFoodList(foodss);
    }

    public void retrieveFoodList(){
        repository.retrieveFoodList();
    }


}
