package com.example.controller;

import com.example.controller.request.SaveUserRequest;
import com.example.controller.request.LoginUserRequest;
import com.example.controller.response.GenericResponse;
import com.example.controller.response.UserResponse;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<GenericResponse> register(@Valid @RequestBody SaveUserRequest request) {
        GenericResponse response = userService.register(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<GenericResponse> login(@Valid @RequestBody LoginUserRequest request) {
        GenericResponse response = userService.login(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
