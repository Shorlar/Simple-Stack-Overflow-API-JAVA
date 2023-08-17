package com.stackOverflowAPI.MyFirstJavaProject.DAO;

import com.stackOverflowAPI.MyFirstJavaProject.Entities.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Questions, Integer> {
    List<Questions> findAllByUsersId(int id);
}
