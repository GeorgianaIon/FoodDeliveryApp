package com.example.fooddeliveryapp.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.fooddeliveryapp.R;

public class AddressFragment extends Fragment {

    Button order;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.address_fragment,  container, false);

        order = view.findViewById(R.id.order);
        order.setOnClickListener(view1 -> {
            Navigation.findNavController(getView()).navigate(R.id.action_addressFragment_to_thankYouFragment);
            Toast.makeText(getActivity(), "Order placed successfully", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}
