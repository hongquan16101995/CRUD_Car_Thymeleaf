package com.example.crud_car_with_thymeleaf.model;

public class Car {
    private static int INDEX = 0;
    private int id;
    private String name;
    private String color;
    private double price;

    public Car() {
    }

    public Car(String name, String color, double price) {
        this.id = ++INDEX;
        this.name = name;
        this.color = color;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }
}
