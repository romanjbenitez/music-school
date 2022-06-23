package com.emusic.school.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Merch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private int stock;
    private String type;
    private double price;
    private String waist;

    public Merch() {}

    public Merch(int stock, String type, double price, String waist) {
        this.stock = stock;
        this.type = type;
        this.price = price;
        this.waist = waist;
    }

    public long getId() {return id;}

    public int getStock() {return stock;}
    public void setStock(int stock) {this.stock = stock;}

    public String getType() {return type;}
    public void setType(String type) {this.type = type;}

    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}

    public String getWaist() {return waist;}
    public void setWaist(String waist) {this.waist = waist;}
}
