package com.course.repositories;

import com.course.entities.WorkEntity;
import org.hibernate.jdbc.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<WorkEntity, Integer> {

    @Procedure(name = WorkEntity.AvgCostForAllCars)
    List<Object> avgCost(@Param("dateStart") LocalDate dateStart, @Param("dateEnd") LocalDate dateEnd);
}
