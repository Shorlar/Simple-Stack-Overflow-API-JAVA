package com.stackOverflowAPI.MyFirstJavaProject.Entities;

import com.stackOverflowAPI.MyFirstJavaProject.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Users implements UserDetails {

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

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return hashedPassword;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
