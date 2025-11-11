package com.example.exception;

import com.example.controller.response.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(CustomErrorException.class)
    public ResponseEntity<GenericResponse> handlerUnauthorizedError(HttpServletRequest req, CustomErrorException ex) {
        log.error(ex.getMessage(), ex);
        GenericResponse response = new GenericResponse();
        response.setMessage(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorHttp()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());

        GenericResponse response = new GenericResponse();
        response.setMessage(errors.toString());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse> handlerGenericError(HttpServletRequest req, Exception ex) {
        log.error(ex.getMessage(), ex);
        GenericResponse response = new GenericResponse();
        response.setMessage("An unexpected error occurred, please contact support.");

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
