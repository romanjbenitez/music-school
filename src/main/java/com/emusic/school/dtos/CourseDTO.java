package com.emusic.school.dtos;

import com.emusic.school.models.Course;
import com.emusic.school.models.Teacher;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class CourseDTO {

    private long id;

    private String level,name;

    private int lessons,price,duration;

    private boolean active;

    private Teacher teacher;

    public CourseDTO() {
    }

    public CourseDTO(Course course) {
        this.id = course.getId();
        this.level = course.getLevel();
        this.name = course.getName();
        this.lessons = course.getLessons();
        this.price = course.getPrice();
        this.duration = course.getDuration();
        this.active = course.isActive();
        this.teacher = course.getTeacher();
    }

    public long getId() {
        return id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLessons() {
        return lessons;
    }

    public void setLessons(int lessons) {
        this.lessons = lessons;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
