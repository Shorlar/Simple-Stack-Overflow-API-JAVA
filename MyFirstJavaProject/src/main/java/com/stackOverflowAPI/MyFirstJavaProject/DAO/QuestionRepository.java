package com.stackOverflowAPI.MyFirstJavaProject.DAO;

import com.stackOverflowAPI.MyFirstJavaProject.Entities.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Questions, Integer> {
}
