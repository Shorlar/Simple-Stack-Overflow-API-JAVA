package com.stackOverflowAPI.MyFirstJavaProject.DAO;

import com.stackOverflowAPI.MyFirstJavaProject.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByEmail(String email);
}
