package com.example.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@ToString
public class SaveUserRequest {

    @NotNull(message = "Name required.")
    @Length(min = 2, max = 100)
    private String name;

    @NotNull(message = "Email required.")
    @Email(message = "Email need to follow pattern.")
    private String email;

    @NotNull(message = "Password required.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password need to follow pattern.")
    private String password;

    @NotNull(message = "Phones required.")
    private List<SaveUserPhoneRequest> phones;
}
