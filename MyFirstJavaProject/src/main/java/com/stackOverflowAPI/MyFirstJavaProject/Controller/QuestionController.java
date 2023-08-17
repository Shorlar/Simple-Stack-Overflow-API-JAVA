package com.stackOverflowAPI.MyFirstJavaProject.Controller;

import com.stackOverflowAPI.MyFirstJavaProject.DTO.CreateQuestionDTO;
import com.stackOverflowAPI.MyFirstJavaProject.DTO.CreateQuestionResponseDTO;
import com.stackOverflowAPI.MyFirstJavaProject.Entities.Questions;
import com.stackOverflowAPI.MyFirstJavaProject.Service.QuestionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class QuestionController {

    private QuestionService questionService;

    public QuestionController(QuestionService service){
        questionService = service;
    }

    @PostMapping("ask-question")
    public ResponseEntity<CreateQuestionResponseDTO> createQuestion(
            @RequestBody() @Valid CreateQuestionDTO createQuestionDTO,
            HttpServletRequest request){
        return ResponseEntity.ok(this.questionService.createQuestion(createQuestionDTO, request));
    }

    @GetMapping("view-questions")
    public ResponseEntity<List<Questions>> viewQuestions(HttpServletRequest request){
            return ResponseEntity.ok(questionService.viewQuestions(request));
    }
}
