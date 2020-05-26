package com.course.repositories;

import com.course.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Procedure(name = UserEntity.UserAuth)
    String isAuthorize(@Param("user_name") String username, @Param("password") String password);

    Optional<UserEntity> getByUsernameAndPassword(String username, String password);

}
