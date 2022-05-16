package com.example.fooddeliveryapp.model;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AppRepository {

    private final Application application;
    private final FirebaseAuth firebaseAuth;
    private MutableLiveData<FirebaseUser> user;
    private MutableLiveData<Boolean> loggedOut;


    public AppRepository(Application application){
         this.application=application;
         firebaseAuth=FirebaseAuth.getInstance();
         user=new MutableLiveData<>();
         loggedOut=new MutableLiveData<>();

         if(firebaseAuth.getCurrentUser() != null){
             user.postValue(firebaseAuth.getCurrentUser());
             loggedOut.postValue(false);
         }
    }

    public void register(String email, String password){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(ContextCompat.getMainExecutor(application), task -> {
                    if(task.isSuccessful()){
                        user.postValue(firebaseAuth.getCurrentUser());
                    }
                    else{
                        Toast.makeText(application, "Registration failed "+task.getException().getMessage()
                                , Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void login(String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(ContextCompat.getMainExecutor(application), task -> {
                    if(task.isSuccessful()){
                        user.postValue(firebaseAuth.getCurrentUser());
                    }
                    else {
                        Toast.makeText(application, "Login failed: "+task.getException().getMessage()
                                ,Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void logOut(){
        firebaseAuth.signOut();
        loggedOut.postValue(true);
    }

    public MutableLiveData<FirebaseUser> getUser() {
        return user;
    }

    public MutableLiveData<Boolean> getLoggedOut() {
        return loggedOut;
    }
}
