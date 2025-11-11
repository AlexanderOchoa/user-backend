package com.example.service;

import com.example.controller.request.LoginUserRequest;
import com.example.controller.request.SaveUserRequest;
import com.example.controller.response.GenericResponse;
import com.example.controller.response.UserResponse;

public interface UserService {
    GenericResponse register(SaveUserRequest request);
    GenericResponse login(LoginUserRequest request);
}
