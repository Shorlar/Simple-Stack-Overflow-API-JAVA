package com.stackOverflowAPI.MyFirstJavaProject.DTO;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserResponseDTO {
    private String displayName;
    private String message;
    private String token;
}
