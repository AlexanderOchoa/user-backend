package com.example.controller.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class UserResponse {

    private String idUser;
    private String name;
    private String email;
    private Integer state;
    private List<UserPhoneResponse> phones;
}
