package com.stackOverflowAPI.MyFirstJavaProject.Service;

import com.stackOverflowAPI.MyFirstJavaProject.DAO.QuestionRepository;
import com.stackOverflowAPI.MyFirstJavaProject.DTO.CreateQuestionDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;

    public QuestionService(QuestionRepository repository){
        questionRepository = repository;
    }

    public void createQuestion(CreateQuestionDTO questionDTO){
//           get user details to add to the instance of question
//        Store the question
    }
}
