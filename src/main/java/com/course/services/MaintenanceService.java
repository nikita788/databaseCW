package com.course.services;

import com.course.entities.MaintenanceEntity;
import com.course.models.MaintenanceDto;
import com.course.repositories.MaintenanceRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaintenanceService {

    private MaintenanceRepository maintenanceRepository;

    public MaintenanceService(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    @Transactional
    public List<MaintenanceDto> getMaintenanceList() {
        return maintenanceRepository.findAll().stream().map(this::toMaintenanceDto).collect(Collectors.toList());
    }

    @Transactional
    public MaintenanceDto create(MaintenanceDto createDto) {
        MaintenanceEntity entity = new MaintenanceEntity();
        fillEntityProperties(entity, createDto);
        maintenanceRepository.save(entity);
        return toMaintenanceDto(maintenanceRepository.getOne(entity.getId()));
    }

    @Transactional
    public MaintenanceDto update(int id, MaintenanceDto updateDto) {
        MaintenanceEntity entity = maintenanceRepository.getOne(id);
        fillEntityProperties(entity, updateDto);
        maintenanceRepository.save(entity);
        return toMaintenanceDto(maintenanceRepository.getOne(entity.getId()));
    }

    @Transactional
    public void delete(int id) {
        maintenanceRepository.deleteById(id);
    }

    private void fillEntityProperties(MaintenanceEntity entity, MaintenanceDto dto) {
        entity.setName(dto.getName());
        entity.setCostOur(dto.getCostOur());
        entity.setCostForeign(dto.getCostForeign());
    }

    MaintenanceDto toMaintenanceDto(MaintenanceEntity entity) {
        MaintenanceDto dto = new MaintenanceDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCostOur(entity.getCostOur());
        dto.setCostForeign(entity.getCostForeign());

        return dto;
    }


}
