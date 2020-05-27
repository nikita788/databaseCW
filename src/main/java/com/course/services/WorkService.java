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

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.course.models.Converter.toCarDto;
import static com.course.models.Converter.toMaintenanceDto;

@Service
public class WorkService {

    private WorkRepository workRepository;
    private MasterRepository masterRepository;
    private CarRepository carRepository;
    private MaintenanceRepository maintenanceRepository;

    @PersistenceContext
    private EntityManager entityManager;

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

        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("AVG_SERVICE_COST_FOR_ALL_CARS")
                .registerStoredProcedureParameter(1, LocalDate.class,
                        ParameterMode.IN)
                .registerStoredProcedureParameter(2, LocalDate.class,
                        ParameterMode.IN)
                .registerStoredProcedureParameter(3, Class.class,
                        ParameterMode.REF_CURSOR)
                .setParameter(1, dateStartt)
                .setParameter(2, dateEndd);

        query.execute();

        List<Object[]> postComments = query.getResultList();

        System.out.println("");
    }

    @Transactional
    public WorkData getWorkData(int id) {
        WorkEntity entity = workRepository.getOne(id);
        return toWorkData(entity);
    }

    @Transactional
    public List<WorkData> getAllWorkData() {
        return workRepository.findAll().stream().map(this::toWorkData).collect(Collectors.toList());
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

    private WorkData toWorkData(WorkEntity entity) {
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
}
