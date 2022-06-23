package com.emusic.school.dtos;

import com.emusic.school.models.Merch;
import com.emusic.school.models.PurchaseOrder;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MerchDto {

    private int stock;
    private String type;
    private double price;
    private String waist;

    private Set<PurchaseOrderDTO> purchaseOrders = new HashSet<>();

    public MerchDto() {
    }

    public MerchDto(Merch merch) {
        this.stock = merch.getStock();
        this.type = merch.getType();
        this.price = merch.getPrice();
        this.waist = merch.getWaist();
        this.purchaseOrders = merch.getPurchaseOrders().stream().map(purchaseOrder -> new PurchaseOrderDTO(purchaseOrder)).collect(Collectors.toSet());
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

    public Set<PurchaseOrderDTO> getPurchaseOrders() {
        return purchaseOrders;
    }
    public void setPurchaseOrders(Set<PurchaseOrderDTO> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }
}
