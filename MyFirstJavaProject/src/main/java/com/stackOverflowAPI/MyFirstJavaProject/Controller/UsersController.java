package com.stackOverflowAPI.MyFirstJavaProject.Controller;

import com.stackOverflowAPI.MyFirstJavaProject.DTO.RegisterUserDTO;
import com.stackOverflowAPI.MyFirstJavaProject.DTO.RegisterUserResponseDTO;
import com.stackOverflowAPI.MyFirstJavaProject.Entities.Users;
import com.stackOverflowAPI.MyFirstJavaProject.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class UsersController {
    private UserService userService;

    @Autowired
    public UsersController(UserService service){
        userService = service;
    }

    @PostMapping("register")
    public ResponseEntity<RegisterUserResponseDTO> registerUser(@RequestBody() @Valid RegisterUserDTO registerUser){
        return new ResponseEntity<>(userService.registerUser(registerUser), HttpStatus.CREATED);
    }
}
