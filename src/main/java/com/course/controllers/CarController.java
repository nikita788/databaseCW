package com.course.controllers;

import com.course.models.CarDto;
import com.course.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "cars", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarController {

    private CarService carService;

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "")
    public List<CarDto> getAll() {
        return carService.getCars();
    }

    @GetMapping(value = "/by-number/{number}")
    public List<CarDto> getCarsByNumber(@PathVariable("number") String number) {
        return carService.getCarsByNumber(number);
    }

    @PutMapping(value = "/{carId}")
    @ResponseStatus(HttpStatus.OK)
    public CarDto update(@PathVariable("carId") int id, @RequestBody CarDto carDto) {
        return carService.updateCar(id, carDto);
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CarDto create(@RequestBody CarDto carDto) {
        return carService.createCar(carDto);
    }

    @DeleteMapping(value = "/{carId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("carId") int id) {
        carService.deleteCar(id);
    }
}
