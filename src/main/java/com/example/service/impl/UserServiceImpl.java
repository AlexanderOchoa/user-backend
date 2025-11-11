package com.example.service.impl;

import com.example.controller.request.LoginUserRequest;
import com.example.controller.request.SaveUserPhoneRequest;
import com.example.controller.request.SaveUserRequest;
import com.example.controller.response.GenericResponse;
import com.example.controller.response.UserPhoneResponse;
import com.example.controller.response.UserResponse;
import com.example.entity.Phone;
import com.example.entity.User;
import com.example.exception.CustomErrorException;
import com.example.repository.PhoneRepository;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PhoneRepository phoneRepository) {
        this.userRepository = userRepository;
        this.phoneRepository = phoneRepository;
    }

    @Override
    public GenericResponse register(SaveUserRequest request) {
        User userEmailInDb = userRepository.findByEmail(request.getEmail());

        validateDataExist(userEmailInDb);

        User userToSave = getUser(request);

        User registeredUser = userRepository.save(userToSave);

        List<Phone> phones = request.getPhones()
                .stream()
                .map(ph -> {
                    Phone phone = getPhone(ph, registeredUser);
                    phone = phoneRepository.save(phone);
                    return phone;
                })
                .collect(Collectors.toList());

        registeredUser.setPhones(phones);

        return getGenericResponse("Success", getGetUserResponse(registeredUser));
    }

    @Override
    public GenericResponse login(LoginUserRequest request) {
        User userEmailInDb = userRepository.findByEmail(request.getEmail());

        validateCredentials(userEmailInDb, request);

        updateDataUser(userEmailInDb);
        User updatedUser = userRepository.save(userEmailInDb);

        return getGenericResponse("Suscess", getGetUserResponse(updatedUser));
    }

    private Phone getPhone(SaveUserPhoneRequest request, User userEmailInDb) {
        Phone phone = new Phone();
        phone.setIPhone(UUID.randomUUID().toString());
        BeanUtils.copyProperties(request, phone);
        phone.setUser(userEmailInDb);

        return phone;
    }

    private User getUser(SaveUserRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        user.setIdUser(UUID.randomUUID().toString());
        user.setCreationDate(new Date());
        user.setToken(UUID.randomUUID().toString());
        user.setLastLogin(new Date());
        user.setState(1);
        user.setPhones(null);

        return user;
    }

    private void updateDataUser(User userEmailInDb) {
        userEmailInDb.setLastLogin(new Date());
    }

    private UserResponse getGetUserResponse(User user) {
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(user, response);

        List<UserPhoneResponse> phones = user.getPhones()
                .stream()
                .map(ph -> {
                    UserPhoneResponse phone = new UserPhoneResponse();
                    BeanUtils.copyProperties(ph, phone);
                    return phone;
                })
                .collect(Collectors.toList());

        response.setPhones(phones);

        return response;
    }

    private GenericResponse getGenericResponse(String message, Object data) {
        GenericResponse responseResponse = new GenericResponse();
        responseResponse.setMessage(message);
        responseResponse.setData(data);
        return responseResponse;
    }

    private void validateDataExist(User userEmailInDb) {
        if (userEmailInDb != null) {
            throw new CustomErrorException("The email of user exist.", HttpStatus.BAD_REQUEST.value());
        }
    }

    private void validateCredentials(User userEmailInDb, LoginUserRequest request) {
        if (userEmailInDb == null ||
                !userEmailInDb.getPassword().equals(request.getPassword())) {
            throw new CustomErrorException("Invalid credentials.", HttpStatus.UNAUTHORIZED.value());
        }
    }

}
