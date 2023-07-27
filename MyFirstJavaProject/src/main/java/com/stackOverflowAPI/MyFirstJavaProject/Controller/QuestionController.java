package com.stackOverflowAPI.MyFirstJavaProject.Controller;

import com.stackOverflowAPI.MyFirstJavaProject.DTO.CreateQuestionDTO;
import com.stackOverflowAPI.MyFirstJavaProject.Service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class QuestionController {

    private QuestionService questionService;

    public QuestionController(QuestionService service){
        questionService = service;
    }

    @GetMapping("question")
    public void createQuestion(CreateQuestionDTO createQuestionDTO){
        this.questionService.createQuestion(createQuestionDTO);
        return;
    }
}
