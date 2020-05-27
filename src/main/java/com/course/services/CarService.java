package com.course.services;

import com.course.entities.CarEntity;
import com.course.models.CarDto;
import com.course.models.Converter;
import com.course.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.course.models.Converter.toCarDto;

@Service
public class CarService {

    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Transactional
    public List<CarDto> getCars() {
        return carRepository.findAll().stream().map(Converter::toCarDto).collect(Collectors.toList());
    }

    @Transactional
    public List<CarDto> getCarsByNumber(String number) {
        return carRepository.getByNumber(number).stream().map(Converter::toCarDto).collect(Collectors.toList());
    }

    @Transactional
    public CarDto updateCar(int carId, CarDto updateDto) {
        CarEntity entity = carRepository.getOne(carId);
        fillEntityProperties(updateDto, entity);
        carRepository.save(entity);
        return toCarDto(carRepository.getOne(carId));
    }

    @Transactional
    public CarDto createCar(CarDto createDto) {
        CarEntity entity = new CarEntity();
        fillEntityProperties(createDto, entity);
        carRepository.save(entity);
        return toCarDto(carRepository.getOne(entity.getId()));
    }

    @Transactional
    public void deleteCar(int carId) {
        carRepository.deleteById(carId);
    }

    private void fillEntityProperties(CarDto dto, CarEntity entity) {
        entity.setNumber(dto.getNumber());
        entity.setColor(dto.getColor());
        entity.setMark(dto.getMark());
        entity.setIsForeign(dto.isForeign() ? 1 : 0);
    }
}
