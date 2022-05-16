package com.example.fooddeliveryapp.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.viewmodel.LoginRegisterViewModel;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginRegisterFragment extends Fragment {
//    private EditText email;
//    private EditText password;
    private Button login;
    private Button register;
    private NavController navController;
    private LoginRegisterViewModel loginRegisterViewModel;
    private TextInputLayout email;
    private TextInputLayout password;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        loginRegisterViewModel= new ViewModelProvider(this).get(LoginRegisterViewModel.class);
        loginRegisterViewModel.getUser().observe(this, firebaseUser -> {
            if(firebaseUser != null){
                navController.navigate(R.id.action_loginRegisterFragment_to_foodFragment);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        navController = Navigation.findNavController(view);
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_loginregister, container, false);

       email=view.findViewById(R.id.email);
       password=view.findViewById(R.id.password);
       login=view.findViewById(R.id.login);
       register=view.findViewById(R.id.register);


        register.setOnClickListener(view12 -> {

           String userEmail=email.getEditText().getText().toString();
           String userPassword=password.getEditText().getText().toString();

           if(userEmail.length()>0 && userPassword.length()>0){

               loginRegisterViewModel.register(userEmail, userPassword);
           }
       });

       login.setOnClickListener(view1 -> {
           String userEmail=email.getEditText().getText().toString();
           String userPassword=password.getEditText().getText().toString();
           Log.i("Login", password.getEditText().getText().toString());


           if(userEmail.length()>0 && userPassword.length()>0){
                loginRegisterViewModel.login(userEmail, userPassword);
           }
       });

        return view;
    }
}
