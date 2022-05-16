package com.example.fooddeliveryapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.model.Food;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private List<Food> foods;


    public FoodAdapter(){
        this.foods = new ArrayList<>();
    }

    public List<Food> getFoods() {
        return foods;
    }

    @NonNull
    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate (R.layout.food_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.ViewHolder holder, int position) {
        holder.foodItem.setText(foods.get(position).getName());
        holder.price.setText(String.valueOf(foods.get(position).getPrice() + "dkk"));
        holder.amount.setText(String.valueOf(foods.get(position).getAmount()));
        Glide.with(holder.itemView).load(foods.get(position).getImage()).into(holder.foodImage);
    }

    @Override
    public int getItemCount() {
       return foods.size();
    }

    public void setItem(List<Food> foods1) {
        this.foods = foods1;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView foodItem;
//        ImageView foodImage;
        TextView price;
        TextView amount;
        FloatingActionButton plus;
        FloatingActionButton minus;
        ShapeableImageView foodImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodItem = itemView.findViewById(R.id.foodItem);
            foodImage = itemView.findViewById(R.id.foodImage);
            price = itemView.findViewById(R.id.price);
            amount = itemView.findViewById(R.id.amount);
            plus = itemView.findViewById(R.id.plus);
            minus = itemView.findViewById(R.id.minus);

            plus.setOnClickListener(view -> {
                Food food = foods.get(getAdapterPosition());
                food.setAmount(food.getAmount()+1);
                amount.setText(String.valueOf(food.getAmount()));
                notifyDataSetChanged();
            });

            minus.setOnClickListener(view -> {
                Food food = foods.get(getAdapterPosition());
                if(food.getAmount()>0){
                    food.setAmount(food.getAmount()-1);
                    amount.setText(String.valueOf(food.getAmount()));
                    notifyDataSetChanged();
                }
                else if(food.getAmount() == 0){
                    amount.setText(String.valueOf(food.getAmount()));
                }
            });
        }
    }
}
