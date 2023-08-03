package com.stackOverflowAPI.MyFirstJavaProject.config;

import com.stackOverflowAPI.MyFirstJavaProject.DAO.UserRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        System.out.println("In bycrpt");
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        System.out.println("In user details service application config");
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new EntityExistsException("User does not exist"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        System.out.println("In Authentication provider");
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        System.out.println("in Authentication manager");
        return  configuration.getAuthenticationManager();
    }
}
