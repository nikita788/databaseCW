package com.course.services;

import com.course.entities.UserEntity;
import com.course.models.UserDto;
import com.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.security.InvalidParameterException;

@Service
public class AuthenticationService {

    private UserRepository userRepository;

    @Autowired
    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void login(UserDto userDto) {
        String founded = userRepository.isAuthorize(userDto.getUsername(), userDto.getPassword());
        if (StringUtils.isEmpty(founded)) {
            throw new InvalidParameterException("User not found!");
        }
    }

    private UserDto toUserDto(UserEntity entity) {
        UserDto dto = new UserDto();
        dto.setUsername(entity.getUsername());
        return dto;
    }

}
