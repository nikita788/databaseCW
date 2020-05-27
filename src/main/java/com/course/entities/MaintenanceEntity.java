package com.course.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SERVICES")
public class MaintenanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "COST_OUR")
    private double costOur;
    @Column(name = "COST_FOREIGN")
    private double costForeign;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCostOur() {
        return costOur;
    }

    public void setCostOur(double costOur) {
        this.costOur = costOur;
    }

    public double getCostForeign() {
        return costForeign;
    }

    public void setCostForeign(double costForeign) {
        this.costForeign = costForeign;
    }
}
