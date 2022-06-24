package com.emusic.school.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class TicketCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ticket_id")
    private Ticket ticket;

    public TicketCourse() {
    }

    public TicketCourse(Course course, Ticket ticket) {
        this.course = course;
        this.ticket = ticket;
    }

    public Long getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
