package com.course.services;

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

}
