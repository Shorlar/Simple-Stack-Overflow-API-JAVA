package com.stackOverflowAPI.MyFirstJavaProject.DAO;

import com.stackOverflowAPI.MyFirstJavaProject.Entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
