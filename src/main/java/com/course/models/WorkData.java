package com.course.models;

import java.time.LocalDate;

public class WorkData {
    private int workId;
    private LocalDate date;
    private MasterDto master;
    private CarDto car;
    private MaintenanceDto maintenance;

    public int getWorkId() {
        return workId;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public MasterDto getMaster() {
        return master;
    }

    public void setMaster(MasterDto master) {
        this.master = master;
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    public MaintenanceDto getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(MaintenanceDto maintenance) {
        this.maintenance = maintenance;
    }
}
