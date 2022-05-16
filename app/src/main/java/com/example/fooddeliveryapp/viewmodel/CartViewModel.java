package com.example.fooddeliveryapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddeliveryapp.model.Food;
import com.example.fooddeliveryapp.repository.FoodRepository;

import java.util.List;

public class CartViewModel extends ViewModel {

    private static FoodRepository repository;

    public CartViewModel(){
        repository = FoodRepository.getInstance();
    }

    public LiveData<List<Food>> getSelectedFood() {
        return repository.getSelectedFood();
    }

    public LiveData<Integer> getTotalPrice(){
        return  repository.getTotalPrice();
    }

    public LiveData<Integer> getTotal() {
        return repository.getTotal();
    }
}
