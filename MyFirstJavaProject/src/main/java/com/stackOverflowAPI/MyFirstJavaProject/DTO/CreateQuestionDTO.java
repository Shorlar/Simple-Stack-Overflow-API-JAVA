package com.stackOverflowAPI.MyFirstJavaProject.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateQuestionDTO {
    @NotBlank(message = "Title should be provided")
    public String title;

    @NotBlank(message = "Question should be provided")
    public String question;
}
