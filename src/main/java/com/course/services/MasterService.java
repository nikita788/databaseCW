package com.course.services;

import com.course.entities.MasterEntity;
import com.course.models.MasterDto;
import com.course.repositories.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MasterService {

    private MasterRepository masterRepository;

    @Autowired
    public void setMasterRepository(MasterRepository masterRepository) {
        this.masterRepository = masterRepository;
    }

    @Transactional
    public List<MasterDto> getMasters() {
        return masterRepository.findAll().stream()
                .map(m -> new MasterDto(m.getId(), m.getName()))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<MasterDto> getMastersByName(String name) {
        return masterRepository.getByName(name).stream()
                .map(m -> new MasterDto(m.getId(), m.getName()))
                .collect(Collectors.toList());
    }

    @Transactional
    public MasterDto updateMaster(int masterId, MasterDto updateDto) {
        MasterEntity entity = masterRepository.getOne(masterId);
        fillEntityProperties(updateDto, entity);
        masterRepository.save(entity);
        return toMasterDto(masterRepository.getOne(masterId));
    }

    @Transactional
    public MasterDto createMaster(MasterDto createDto) {
        MasterEntity entity = new MasterEntity();
        fillEntityProperties(createDto, entity);
        masterRepository.save(entity);
        return toMasterDto(masterRepository.getOne(entity.getId()));
    }

    @Transactional
    public void deleteMaster(int masterId) {
        masterRepository.deleteById(masterId);
    }

    private void fillEntityProperties(MasterDto dto, MasterEntity entity) {
        entity.setName(dto.getName());
    }

    private MasterDto toMasterDto(MasterEntity entity) {
        return new MasterDto(entity.getId(), entity.getName());
    }

}
