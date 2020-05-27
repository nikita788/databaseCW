package com.course.controllers;

import com.course.models.MasterDto;
import com.course.services.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(value = "/{masterId}")
    @ResponseStatus(HttpStatus.OK)
    public MasterDto update(@PathVariable("masterId") int id, @RequestBody MasterDto masterDto) {
        return masterService.updateMaster(id, masterDto);
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public MasterDto create(@RequestBody MasterDto masterDto) {
        return masterService.createMaster(masterDto);
    }

    @DeleteMapping(value = "/{masterId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("masterId") int id) {
        masterService.deleteMaster(id);
    }
}
