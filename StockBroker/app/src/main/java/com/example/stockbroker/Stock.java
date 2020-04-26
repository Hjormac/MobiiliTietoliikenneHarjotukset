package com.example.stockbroker;

public class Stock {

    private String name;
    private double price;

    public Stock(String n , double p) {
        this.name = n;
        this.price = p;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
