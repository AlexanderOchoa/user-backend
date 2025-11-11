package com.example.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomErrorException extends RuntimeException {

    private int errorHttp;

    public CustomErrorException(String message) {
        super(message);
    }

    public CustomErrorException(String message, int errorHttp) {
        super(message);
        this.errorHttp = errorHttp;
    }

}
