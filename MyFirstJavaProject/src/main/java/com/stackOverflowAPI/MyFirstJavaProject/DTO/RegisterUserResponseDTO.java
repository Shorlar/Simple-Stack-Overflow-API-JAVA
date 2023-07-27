package com.stackOverflowAPI.MyFirstJavaProject.DTO;

import com.stackOverflowAPI.MyFirstJavaProject.Entities.Users;
import lombok.Data;

@Data
public class RegisterUserResponseDTO {
    private int id;
    private  String displayName;
    private String message;

    public RegisterUserResponseDTO(Users user){
        id = user.getId();
        displayName = user.getDisplayName();
        message = "User Successfully created";
    }
}
