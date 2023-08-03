package com.stackOverflowAPI.MyFirstJavaProject.DTO;

import com.stackOverflowAPI.MyFirstJavaProject.Entities.Users;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class RegisterUserResponseDTO {
    private String displayName;
    private String message;
    private String token;
}
