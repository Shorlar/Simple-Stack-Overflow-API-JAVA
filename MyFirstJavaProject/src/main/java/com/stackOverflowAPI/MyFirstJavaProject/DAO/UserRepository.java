package com.stackOverflowAPI.MyFirstJavaProject.DAO;

import com.stackOverflowAPI.MyFirstJavaProject.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);
}
