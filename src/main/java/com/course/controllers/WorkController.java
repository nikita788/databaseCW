package com.course.controllers;

import com.course.models.AvgCostDto;
import com.course.models.AvgCostOutDto;
import com.course.models.WorkCreateDto;
import com.course.models.WorkData;
import com.course.services.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "works", produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkController {

    private WorkService workService;

    @Autowired
    public void setWorkService(WorkService workService) {
        this.workService = workService;
    }

    @GetMapping(value = "/{workId}")
    public WorkData getWorkDataById(@PathVariable("workId") int id) {
        return workService.getWorkData(id);
    }

    @GetMapping(value = "")
    public List<WorkData> getAllWorkData() {
        return workService.getAllWorkData();
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public WorkData createWork(@RequestBody WorkCreateDto createDto) {
        return workService.createWork(createDto);
    }

    @PostMapping(value = "/avgCost", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public AvgCostOutDto avgCost(@RequestBody @Valid AvgCostDto avgCostDto) {
        return workService.avgCost(avgCostDto.getDateStart(), avgCostDto.getDateEnd());
    }
}
