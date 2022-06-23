package com.emusic.school.dtos;

import com.emusic.school.models.Merch;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MerchDTO {
    private long id;
    private int stock;
    private String type;
    private double price;
    private String waist;

    public MerchDTO() {
    }

    public MerchDTO(Merch merch) {
        this.id = merch.getId();
        this.stock = merch.getStock();
        this.type = merch.getType();
        this.price = merch.getPrice();
        this.waist = merch.getWaist();
    }

    public long getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getWaist() {
        return waist;
    }
    public void setWaist(String waist) {
        this.waist = waist;
    }

}
