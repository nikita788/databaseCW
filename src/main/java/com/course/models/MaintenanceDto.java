package com.course.models;

public class MaintenanceDto {
    private int id;
    private String name;
    private double costOur;
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
