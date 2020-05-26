package com.course.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CARS")
public class CarEntity {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "NUM")
    private String number;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "MARK")
    private String mark;

    @Column(name = "IS_FOREIGN")
    private int isForeign;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getIsForeign() {
        return isForeign;
    }

    public void setIsForeign(int isForeign) {
        this.isForeign = isForeign;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
