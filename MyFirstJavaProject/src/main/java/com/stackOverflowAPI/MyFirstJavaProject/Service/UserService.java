package com.stackOverflowAPI.MyFirstJavaProject.Service;

import com.stackOverflowAPI.MyFirstJavaProject.DAO.UserRepository;
import com.stackOverflowAPI.MyFirstJavaProject.DTO.RegisterUserDTO;
import com.stackOverflowAPI.MyFirstJavaProject.DTO.RegisterUserResponseDTO;
import com.stackOverflowAPI.MyFirstJavaProject.Entities.Users;
import com.stackOverflowAPI.MyFirstJavaProject.ExceptionHandler.DatabaseException;
import com.stackOverflowAPI.MyFirstJavaProject.Role;
import com.stackOverflowAPI.MyFirstJavaProject.config.JwtService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;

    public RegisterUserResponseDTO registerUser(RegisterUserDTO userDetails) {
        System.out.println("In register user method");
        Users newUser = new Users();
        newUser.setAboutMe(userDetails.aboutMe);
        newUser.setEmail(userDetails.email);
        newUser.setDisplayName(userDetails.displayName);
        newUser.setHashedPassword(bCryptPasswordEncoder.encode(userDetails.password));
        newUser.setRole(Role.USER);
        try{
            Users registeredUser = userRepository.save(newUser);
            String jwt = jwtService.generateToken(registeredUser);
            RegisterUserResponseDTO createdUser = new RegisterUserResponseDTO()
                    .builder()
                    .displayName(registeredUser.getDisplayName())
                    .message("Successful")
                    .token(jwt)
                    .build();
            System.out.println(createdUser.toString());
            return createdUser;
        }catch (Exception ex){
            throw new DatabaseException();
        }
    }
}
