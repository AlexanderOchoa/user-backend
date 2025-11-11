package com.example.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SaveUserPhoneRequest {

    private String number;

    @JsonProperty(value = "citycode")
    private String cityCode;

    @JsonProperty(value = "contrycode")
    private String contryCode;
}
