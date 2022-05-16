package com.example.fooddeliveryapp.model;

public class Food {
    private String name;
    private int price;
    private int amount;
    private String image;

    public Food(String name, int price, String image){
        this.amount=0;
        this.name=name;
        this.price=price;
        this.image=image;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
