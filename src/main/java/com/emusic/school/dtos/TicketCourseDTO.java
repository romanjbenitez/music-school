package com.emusic.school.dtos;

import com.emusic.school.models.TicketCourse;

public class TicketCourseDTO {
    private Long idClient, idCourse;
    private String nameCourse;

    public TicketCourseDTO() {
    }

    public TicketCourseDTO(TicketCourse ticketCourse) {
        this.idClient = ticketCourse.getCourse().getId();
        this.idCourse = ticketCourse.getCourse().getId();
        this.nameCourse = ticketCourse.getCourse().getName();
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Long idCourse) {
        this.idCourse = idCourse;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }
}
