package com.course.services;

import com.course.entities.CarEntity;
import com.course.entities.MaintenanceEntity;
import com.course.entities.MasterEntity;
import com.course.entities.WorkEntity;
import com.course.models.MasterDto;
import com.course.models.WorkCreateDto;
import com.course.models.WorkData;
import com.course.repositories.CarRepository;
import com.course.repositories.MaintenanceRepository;
import com.course.repositories.MasterRepository;
import com.course.repositories.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.course.models.Converter.toCarDto;
import static com.course.models.Converter.toMaintenanceDto;

@Service
public class WorkService {

    private WorkRepository workRepository;
    private MasterRepository masterRepository;
    private CarRepository carRepository;
    private MaintenanceRepository maintenanceRepository;

    @Autowired
    public WorkService(WorkRepository workRepository, MasterRepository masterRepository,
                       CarRepository carRepository, MaintenanceRepository maintenanceRepository) {
        this.workRepository = workRepository;
        this.masterRepository = masterRepository;
        this.maintenanceRepository = maintenanceRepository;
        this.carRepository = carRepository;
    }

    @Transactional
    public void avgCost(String dateStart, String dateEnd) {
        LocalDate dateStartt = LocalDate.parse(dateStart);
        LocalDate dateEndd = LocalDate.parse(dateEnd);
        List<Object> list = workRepository.avgCost(dateStartt, dateEndd);
        System.out.println(list);
    }

    @Transactional
    public WorkData getWorkData(int id) {
        WorkEntity entity = workRepository.getOne(id);
        WorkData workData = new WorkData();
        workData.setWorkId(entity.getId());
        workData.setDate(entity.getDate());
        if (entity.getMaster() != null) {
            workData.setMaster(new MasterDto(entity.getMaster().getId(), entity.getMaster().getName()));
        }
        if (entity.getCar() != null) {
            workData.setCar(toCarDto(entity.getCar()));
        }
        if (entity.getService() != null) {
            workData.setMaintenance(toMaintenanceDto(entity.getService()));
        }
        return workData;
    }

    @Transactional
    public WorkData createWork(WorkCreateDto createDto) {
        WorkEntity entity = new WorkEntity();
        MasterEntity masterEntity = masterRepository.getOne(createDto.getMasterId());
        CarEntity carEntity = carRepository.getOne(createDto.getCarId());
        MaintenanceEntity maintenanceEntity = maintenanceRepository.getOne(createDto.getMaintenanceId());
        entity.setDate(LocalDate.parse(createDto.getWorkDate()));
        entity.setMaster(masterEntity);
        entity.setCar(carEntity);
        entity.setService(maintenanceEntity);
        workRepository.save(entity);

        return getWorkData(entity.getId());
    }
}
