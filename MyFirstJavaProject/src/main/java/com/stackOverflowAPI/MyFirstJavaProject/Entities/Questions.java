package com.stackOverflowAPI.MyFirstJavaProject.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(columnDefinition = "boolean default true")
    private Boolean subscribeAnswer;

    @Column(columnDefinition = "int default 0")
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
