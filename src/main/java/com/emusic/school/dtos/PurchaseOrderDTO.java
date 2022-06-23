package com.emusic.school.dtos;

import com.emusic.school.models.Merch;
import com.emusic.school.models.PurchaseOrder;
import com.emusic.school.models.Ticket;

public class PurchaseOrderDTO {

    private Ticket ticket;
    private Merch merch;

    public PurchaseOrderDTO() {
    }

    public PurchaseOrderDTO(PurchaseOrder purchaseOrder) {
        this.ticket = purchaseOrder.getTicket();
        this.merch = purchaseOrder.getMerch();
    }

    public Ticket getTicket() {
        return ticket;
    }
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Merch getMerch() {
        return merch;
    }
    public void setMerch(Merch merch) {
        this.merch = merch;
    }
}
