package com.course.models;

import com.course.entities.CarEntity;
import com.course.entities.MaintenanceEntity;

public class Converter {
    private Converter() {}

    public static MaintenanceDto toMaintenanceDto(MaintenanceEntity entity) {
        MaintenanceDto dto = new MaintenanceDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCostOur(entity.getCostOur());
        dto.setCostForeign(entity.getCostForeign());

        return dto;
    }

    public static CarDto toCarDto(CarEntity entity) {
        CarDto dto = new CarDto();
        dto.setId(entity.getId());
        dto.setNumber(entity.getNumber());
        dto.setColor(entity.getColor());
        dto.setMark(entity.getMark());
        dto.setForeign(entity.getIsForeign() == 1);
        return dto;
    }
}
