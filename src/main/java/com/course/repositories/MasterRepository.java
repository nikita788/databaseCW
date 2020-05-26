package com.course.repositories;

import com.course.entities.MasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterRepository extends JpaRepository<MasterEntity, Integer> {
    List<MasterEntity> getByName(String name);
}
