package com.course.repositories;

import com.course.entities.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Integer> {
    List<CarEntity> getByNumber(String number);
    CarEntity findById(int id);
}
