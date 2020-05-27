package com.course.entities;

import javax.persistence.*;
import java.time.LocalDate;

@NamedStoredProcedureQuery(name = WorkEntity.AvgCostForAllCars,
        procedureName = "AVG_SERVICE_COST_FOR_ALL_CARS",
        parameters = {
                @StoredProcedureParameter(type = LocalDate.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(type = LocalDate.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(type = Object.class, mode = ParameterMode.REF_CURSOR)
        })

@Entity
@Table(name = "WORKS")
public class WorkEntity {

    public static final String AvgCostForAllCars = "avgCost";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "DATE_WORK")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MASTER_ID", referencedColumnName = "ID")
    private MasterEntity master;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAR_ID", referencedColumnName = "ID")
    private CarEntity car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SERVICE_ID", referencedColumnName = "ID")
    private MaintenanceEntity service;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public MasterEntity getMaster() {
        return master;
    }

    public void setMaster(MasterEntity master) {
        this.master = master;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    public MaintenanceEntity getService() {
        return service;
    }

    public void setService(MaintenanceEntity service) {
        this.service = service;
    }
}
