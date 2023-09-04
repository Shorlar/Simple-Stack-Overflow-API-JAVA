package com.stackOverflowAPI.MyFirstJavaProject.Service;

import com.stackOverflowAPI.MyFirstJavaProject.DAO.AnswerRepository;
import com.stackOverflowAPI.MyFirstJavaProject.DAO.QuestionRepository;
import com.stackOverflowAPI.MyFirstJavaProject.DTO.AnswerQuestionDTO;
import com.stackOverflowAPI.MyFirstJavaProject.DTO.CreateQuestionDTO;
import com.stackOverflowAPI.MyFirstJavaProject.DTO.CreateQuestionResponseDTO;
import com.stackOverflowAPI.MyFirstJavaProject.Entities.Answer;
import com.stackOverflowAPI.MyFirstJavaProject.Entities.Questions;
import com.stackOverflowAPI.MyFirstJavaProject.Entities.Users;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository repository;
    private final AnswerRepository answerRepository;

    public CreateQuestionResponseDTO createQuestion(CreateQuestionDTO questionDTO, HttpServletRequest request){

        Users user = (Users) request.getAttribute("currentUser");
        Questions questions = Questions
                .builder()
                .questionBody(questionDTO.getQuestion())
                .title(questionDTO.title)
                .userDisplayName(user.getDisplayName())
                .users(user)
                .build();
        repository.save(questions);
        CreateQuestionResponseDTO response = CreateQuestionResponseDTO
                .builder()
                .question(questions.getQuestionBody())
                .createdTime(Date.from(questions.getCreatedTime()))
                .displayName(questions.getUserDisplayName())
                .score(questions.getScore())
                .title(questions.getTitle())
                .build();
        return response;
    }

    public List<Questions> viewQuestions(HttpServletRequest request){
        Users user = (Users) request.getAttribute("currentUser");
        List<Questions> questions = repository.findAllByUsersId(user.getId());
        System.out.println(questions);
        return questions;
    }

    public String answerQuestion(HttpServletRequest request, AnswerQuestionDTO answerQuestionDTO){
        Users user = (Users) request.getAttribute("currentUser");
        int questionId = answerQuestionDTO.getQuestionId();
        Questions question = this.repository.findById(questionId).orElseThrow();
        String userDisplayName = user.getDisplayName();
        Answer answer = Answer.builder()
                .userDisplayName(userDisplayName)
                .answerBody(answerQuestionDTO.getAnswer())
//                .question(question)
                .users(user)
                .build();
        question.add(answer);
        repository.save(question);
        return "Successful";
    }
}
