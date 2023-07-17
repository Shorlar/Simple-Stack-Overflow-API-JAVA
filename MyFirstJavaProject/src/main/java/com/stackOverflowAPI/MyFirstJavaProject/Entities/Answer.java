package com.stackOverflowAPI.MyFirstJavaProject.Entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String userDisplayName;

    @Column(name = "answer")
    private String answerBody;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Questions question;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @CreationTimestamp
    private Instant createdTime;
}
