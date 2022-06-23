package com.emusic.school.dtos;

import com.emusic.school.models.Client;
import com.emusic.school.models.PurchaseOrder;
import com.emusic.school.models.Ticket;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TicketDTO {
    private long id;
    private double totalPrice;
    private Set<PurchaseOrderDTO> purchaseOrder = new HashSet<>();

    public TicketDTO() {
    }

    public TicketDTO(Ticket ticket) {
        this.id = ticket.getId();
        this.totalPrice = ticket.getTotalPrice();
        this.purchaseOrder = ticket.getPurchaseOrder().stream().map(PurchaseOrderDTO::new).collect(Collectors.toSet());
    }

    public long getId() {
        return id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<PurchaseOrderDTO> getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(Set<PurchaseOrderDTO> purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }
}
