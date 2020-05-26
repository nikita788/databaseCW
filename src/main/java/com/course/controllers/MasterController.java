package com.course.controllers;

import com.course.models.MasterDto;
import com.course.services.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "masters", produces = MediaType.APPLICATION_JSON_VALUE)
public class MasterController {

    private MasterService masterService;

    @Autowired
    public void setMasterService(MasterService masterService) {
        this.masterService = masterService;
    }

    @GetMapping(value = "")
    public List<MasterDto> getAll() {
        return masterService.getMasters();
    }

    @GetMapping(value = "/{name}")
    public List<MasterDto> getMastersByName(@PathVariable(name = "name") String name) {
        return masterService.getMastersByName(name);
    }
}
