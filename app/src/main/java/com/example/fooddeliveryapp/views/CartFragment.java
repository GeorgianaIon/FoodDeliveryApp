package com.example.fooddeliveryapp.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryapp.model.Food;
import com.example.fooddeliveryapp.adapter.ProductAdapter;
import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.viewmodel.CartViewModel;
import com.example.fooddeliveryapp.viewmodel.FoodViewModel;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    private RecyclerView recyclerView;
    private FoodFragment food;
    private CartViewModel viewModel;
    private TextView cartBill;
    private Button order;
    private TextView total;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_layout,  container, false);

        viewModel = new ViewModelProvider(this).get(CartViewModel.class);

        cartBill = view.findViewById(R.id.cartTotal);
        order = view.findViewById(R.id.placeOrder);
        total = view.findViewById(R.id.total);

        order.setOnClickListener(order -> Toast.makeText(getActivity(), "Order placed successfully", Toast.LENGTH_SHORT).show());

        viewModel.getTotalPrice().observe(getViewLifecycleOwner(), integer -> cartBill.setText("Cart total: " + integer + "dkk"));

        viewModel.getTotal().observe(getViewLifecycleOwner(), totalTotal -> total.setText("Total of " + totalTotal + "dkk"));

        recyclerView = view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.hasFixedSize();
        food = new FoodFragment();

        ProductAdapter adapter = new ProductAdapter(food.getFoods());
        recyclerView.setAdapter(adapter);

        viewModel.getSelectedFood().observe(getViewLifecycleOwner(), items -> {
            adapter.setItem(items);
        });


        return view;
    }

}
