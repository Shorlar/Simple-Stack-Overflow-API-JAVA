package com.stackOverflowAPI.MyFirstJavaProject.Service;

import com.stackOverflowAPI.MyFirstJavaProject.DAO.UserRepository;
import com.stackOverflowAPI.MyFirstJavaProject.DTO.RegisterUserDTO;
import com.stackOverflowAPI.MyFirstJavaProject.DTO.RegisterUserResponseDTO;
import com.stackOverflowAPI.MyFirstJavaProject.DTO.SignInDTO;
import com.stackOverflowAPI.MyFirstJavaProject.Entities.Users;
import com.stackOverflowAPI.MyFirstJavaProject.ExceptionHandler.DatabaseException;
import com.stackOverflowAPI.MyFirstJavaProject.Role;
import com.stackOverflowAPI.MyFirstJavaProject.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
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
            RegisterUserResponseDTO createdUser = RegisterUserResponseDTO
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

    public RegisterUserResponseDTO signIn(SignInDTO userDetails){

    try{
        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Users user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);
        return RegisterUserResponseDTO.builder()
                .displayName(user.getDisplayName())
                .message("Successful")
                .token(token).build();
    }catch (Exception e){
        throw new DatabaseException(e.getMessage(), e);
    }

}
}
