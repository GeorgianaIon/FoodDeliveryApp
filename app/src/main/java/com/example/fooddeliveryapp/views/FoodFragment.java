package com.example.fooddeliveryapp.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryapp.model.Food;
import com.example.fooddeliveryapp.adapter.FoodAdapter;
import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.viewmodel.FoodViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class FoodFragment extends Fragment {

    private FloatingActionButton fab;
    private TextView loggedInUser;
    private Button logOutButton;
    private RecyclerView recyclerView;
    List<Food> foods;
    private FoodViewModel viewModel;

    public FoodFragment(){
        foods = new ArrayList<>();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(FoodViewModel.class);
        viewModel.getUser().observe(this, firebaseUser -> {
            if(firebaseUser != null){
                loggedInUser.setText("Logged in user: " + firebaseUser.getEmail());
            }
        });

        viewModel.getLoggedOut().observe(this, loggedOut -> {
            if(loggedOut){
                Navigation.findNavController(getView()).navigate(R.id.action_foodFragment_to_loginRegisterFragment);
            }

        });
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_fragment,  container, false);

        loggedInUser=view.findViewById(R.id.textView);
        logOutButton=view.findViewById(R.id.logOut);
        fab = view.findViewById(R.id.action_bar);
        recyclerView = view.findViewById(R.id.rv);
        
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.hasFixedSize();

        FoodAdapter adapter = new FoodAdapter();
        recyclerView.setAdapter(adapter);

        viewModel.getFood().observe(getViewLifecycleOwner(), foods1 -> {
            adapter.setItem(foods1);

        });

        logOutButton.setOnClickListener(view1 -> viewModel.logOut());

        fab.setOnClickListener(view12 -> {
            viewModel.addToCart(adapter.getFoods());
            Navigation.findNavController( getView()).navigate(R.id.action_foodFragment_to_cartFragment);
        });

        viewModel.retrieveFoodList();

        return view;
    }

    public List<Food> getFoods() {
        return foods;
    }
}
