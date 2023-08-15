package com.stackOverflowAPI.MyFirstJavaProject.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateQuestionResponseDTO {

    public String question;
    public String title;
    public int score;
    public String displayName;
    public Date createdTime;
}
