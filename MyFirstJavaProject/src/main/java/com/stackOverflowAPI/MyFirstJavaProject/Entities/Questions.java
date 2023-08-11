package com.stackOverflowAPI.MyFirstJavaProject.Entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Data
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String userDisplayName;

    @Column()
    private String questionBody;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean subscribeAnswer;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int score;

    @CreationTimestamp
    private Instant createdTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    private List<Answer> answers;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    private List<Votes> votes;
}
