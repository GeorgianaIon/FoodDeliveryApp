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

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Food> foods;

    public ProductAdapter(List<Food> foods){
        this.foods = foods;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate (R.layout.food_item, parent, false);
        return new ProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        holder.foodItem.setText(foods.get(position).getName());
        holder.price.setText(String.valueOf(foods.get(position).getPrice() + "dkk"));
        holder.amount.setText(String.valueOf(foods.get(position).getAmount()));
        Glide.with(holder.itemView).load(foods.get(position).getImage()).into(holder.foodImage);
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public void setItem(List<Food> items) {
        this.foods = items;
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView foodItem;
        ImageView foodImage;
        TextView price;
        TextView amount;
        FloatingActionButton plus;
        FloatingActionButton minus;

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
                notifyItemChanged(getAdapterPosition());
            });

            minus.setOnClickListener(view -> {
                Food food = foods.get(getAdapterPosition());
                if(food.getAmount()>1){
                    amount.setText(String.valueOf(food.getAmount()));
                    notifyItemChanged(getAdapterPosition());
                }
                else if(food.getAmount() == 1){
                    foods.remove(food);
                    notifyItemRemoved(getAdapterPosition());
                    amount.setText(String.valueOf(food.getAmount()));
                }
                food.setAmount(food.getAmount()-1);
            });
        }

    }
}
