package com.emusic.school.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String level,name;

    private int lessons,price,duration;

    private boolean active;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="teacher_id")
    private Teacher teacher;
    public Course() {
    }

    public Course(String level, String name, int lessons, int price, int duration, boolean active) {
        this.level = level;
        this.name = name;
        this.lessons = lessons;
        this.price = price;
        this.duration = duration;
        this.active = active;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
}
