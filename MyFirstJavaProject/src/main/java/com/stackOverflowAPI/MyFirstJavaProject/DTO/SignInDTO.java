package com.stackOverflowAPI.MyFirstJavaProject.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class SignInDTO {

    public String username;

    public String password;
}
