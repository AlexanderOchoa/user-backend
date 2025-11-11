package com.example.controller.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserPhoneResponse {

    private String iPhone;
    private String number;
    private String cityCode;
    private String contryCode;
}
