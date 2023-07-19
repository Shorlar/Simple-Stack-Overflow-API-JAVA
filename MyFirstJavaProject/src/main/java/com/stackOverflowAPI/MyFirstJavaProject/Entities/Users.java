package com.stackOverflowAPI.MyFirstJavaProject.Entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "displayName", unique = true)
    private String displayName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "hashedPassword")
    private String hashedPassword;

    @Column(name = "aboutMe", nullable = true)
    private String aboutMe;

    @CreationTimestamp
    private Instant createdTime;

    @OneToMany(mappedBy = "users")
    private List<Questions> questions;

    @OneToMany(mappedBy = "users")
    private List<Votes> votes;

    @OneToMany(mappedBy = "users")
    private List<Answer> answers;
}
