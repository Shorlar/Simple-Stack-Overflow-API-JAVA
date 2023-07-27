package com.stackOverflowAPI.MyFirstJavaProject.Service;

import com.stackOverflowAPI.MyFirstJavaProject.DAO.UserRepository;
import com.stackOverflowAPI.MyFirstJavaProject.DTO.RegisterUserDTO;
import com.stackOverflowAPI.MyFirstJavaProject.DTO.RegisterUserResponseDTO;
import com.stackOverflowAPI.MyFirstJavaProject.Entities.Users;
import com.stackOverflowAPI.MyFirstJavaProject.ExceptionHandler.DatabaseException;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private  final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository repository, BCryptPasswordEncoder encoder){
        userRepository = repository;
        bCryptPasswordEncoder = encoder;
    }


    public RegisterUserResponseDTO registerUser(RegisterUserDTO userDetails) {
        Users newUser = new Users();
        newUser.setAboutMe(userDetails.aboutMe);
        newUser.setEmail(userDetails.email);
        newUser.setDisplayName(userDetails.displayName);
        newUser.setHashedPassword(bCryptPasswordEncoder.encode(userDetails.password));
        Optional<Users> userExists = Optional.ofNullable(userRepository.findByEmail(userDetails.email));
        if(userExists.isEmpty()){
            try{
                Users registeredUser = userRepository.save(newUser);
                RegisterUserResponseDTO createdUser = new RegisterUserResponseDTO(registeredUser);
                System.out.println(createdUser.toString());
                return createdUser;
            }catch (Exception ex){
                throw new DatabaseException();
            }
        };
        throw new EntityExistsException("User with " + userDetails.getEmail() + " already exists!");
    }
}
