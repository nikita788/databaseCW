package com.course.services;

import com.course.entities.CarEntity;
import com.course.models.CarDto;
import com.course.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Transactional
    public List<CarDto> getCars() {
        return carRepository.findAll().stream().map(this::toCarDto).collect(Collectors.toList());
    }

    @Transactional
    public List<CarDto> getCarsByNumber(String number) {
        return carRepository.getByNumber(number).stream().map(this::toCarDto).collect(Collectors.toList());
    }

    @Transactional
    public CarDto updateCar(int carId, CarDto updateDto) {
        CarEntity entity = carRepository.getOne(carId);
        entity.setNumber(updateDto.getNumber());
        entity.setColor(updateDto.getColor());
        entity.setMark(updateDto.getMark());
        entity.setIsForeign(updateDto.isForeign() ? 1 : 0);
        carRepository.save(entity);
        return toCarDto(carRepository.getOne(carId));
    }

    private CarDto toCarDto(CarEntity entity) {
        CarDto dto = new CarDto();
        dto.setId(entity.getId());
        dto.setNumber(entity.getNumber());
        dto.setColor(entity.getColor());
        dto.setMark(entity.getMark());
        dto.setForeign(entity.getIsForeign() == 1);
        return dto;
    }

}
