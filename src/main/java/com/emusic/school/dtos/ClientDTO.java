package com.emusic.school.dtos;

import com.emusic.school.models.Client;
import com.emusic.school.models.Ticket;

public class ClientDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isActive;
    private Set<TicketDTO> tickets = new HashSet<>();


    public ClientDTO() {}

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.password = client.getPassword();
        this.isActive = client.isActive();
        this.tickets = client.getTickets().stream().map(ticket -> new TicketDTO(ticket)).collect(toSet());
    }

    public long getId() {return id;}


    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public boolean isActive() {return isActive;}
    public void setActive(boolean active) {isActive = active;}

    public Set<TicketDTO> getTickets() {return tickets;}
    public void setTickets(Set<TicketDTO> tickets) {this.tickets = tickets;}
}
