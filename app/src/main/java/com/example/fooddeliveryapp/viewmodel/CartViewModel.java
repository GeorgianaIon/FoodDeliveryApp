package com.example.fooddeliveryapp.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddeliveryapp.model.Food;
import com.example.fooddeliveryapp.model.FoodRepository;

import java.util.List;

public class CartViewModel extends ViewModel {

    private static FoodRepository repository;

    public CartViewModel(){
        repository = FoodRepository.getInstance();
    }

    public LiveData<List<Food>> getSelectedFood() {
        return repository.getSelectedFood();
    }

//    public void insert(final Food food) {
//        repository.insert(food);
//    }
//
//    public void remove(final Food food){
//        repository.deleteFood(food);
//    }

    public LiveData<Integer> getTotalPrice(){
        return  repository.getTotalPrice();
    }

}
