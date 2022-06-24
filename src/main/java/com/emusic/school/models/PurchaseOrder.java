package com.emusic.school.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ticket_id")
    private Ticket ticket;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="merch_id")
    private Merch merch;

    public PurchaseOrder() {}

    public PurchaseOrder(Ticket ticket, Merch merch) {
        this.ticket = ticket;
        this.merch = merch;
    }

    public Long getId() {return id;}

    public Ticket getTicket() {return ticket;}
    public void setTicket(Ticket ticket) {this.ticket = ticket;}

    public Merch getMerch() {return merch;}
    public void setMerch(Merch merch) {this.merch = merch;}
}
