package com.course.controllers;

import com.course.models.UserDto;
import com.course.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorizationController {

    private AuthenticationService authenticationService;

    @Autowired
    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.OK)
    public void auth(@RequestBody @Valid UserDto userDto) {
        authenticationService.login(userDto);
    }

}
