package com.example.fooddeliveryapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.fooddeliveryapp.repository.AppRepository;
import com.google.firebase.auth.FirebaseUser;

public class LoginRegisterViewModel extends AndroidViewModel {
    private AppRepository appRepository;
    private MutableLiveData<FirebaseUser> user;

    public LoginRegisterViewModel(@NonNull Application application) {
        super(application);
        appRepository=new AppRepository(application);
        user= appRepository.getUser();
    }

    public void register(String email, String password){
        appRepository.register(email, password);
    }

    public void login(String email, String password) {
        appRepository.login(email, password);
    }

    public MutableLiveData<FirebaseUser> getUser() {
        return user;
    }
}
