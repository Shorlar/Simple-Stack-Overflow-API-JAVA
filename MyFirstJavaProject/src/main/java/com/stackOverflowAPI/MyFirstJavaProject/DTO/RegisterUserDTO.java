package com.stackOverflowAPI.MyFirstJavaProject.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterUserDTO {

    @NotBlank(message = "Display name must be provided")
    public String displayName;

    @Email
    public  String email;

    @NotBlank(message = "password must be provided")
    public  String password;
    public String aboutMe;
}
