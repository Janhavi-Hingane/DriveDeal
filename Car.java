package com.drivedeal.model;
public class Car {
  private int id; private String make; private String model; private int year;
  private double price; private int mileage; private String fuelType; private String description; private String imageUrl;
  public int getId(){return id;} public void setId(int id){this.id=id;}
  public String getMake(){return make;} public void setMake(String make){this.make=make;}
  public String getModel(){return model;} public void setModel(String model){this.model=model;}
  public int getYear(){return year;} public void setYear(int year){this.year=year;}
  public double getPrice(){return price;} public void setPrice(double price){this.price=price;}
  public int getMileage(){return mileage;} public void setMileage(int mileage){this.mileage=mileage;}
  public String getFuelType(){return fuelType;} public void setFuelType(String fuelType){this.fuelType=fuelType;}
  public String getDescription(){return description;} public void setDescription(String description){this.description=description;}
  public String getImageUrl(){return imageUrl;} public void setImageUrl(String imageUrl){this.imageUrl=imageUrl;}
}