package com.E_Commerce.API.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.E_Commerce.API.Authentication.JwtUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public UserResponse registerUser(UserRequestDto request) {
        UserModel userEmail = userRepository.findByEmail(request.getEmail());
        UserModel userUserName = userRepository.findByUserName(request.getUserName());
        if (userEmail != null || userUserName != null) {
            throw new RuntimeException("User already exists");
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        UserModel user = UserModel
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .userName(request.getUserName())
                .email(request.getEmail())
                .password(encodedPassword)
                .build();
        userRepository.save(user);
        String jwtToken = jwtUtils.generateToken(request.getEmail());
        return new UserResponse(user.getFirstName(),
                user.getLastName(),
                user.getUserName(),
                user.getEmail(),
                jwtToken);
    }

    public UserResponse login(UserRequestLogin request) {
        String email = request.email();
        String password = request.password();
        UserModel user = userRepository.findByEmail(email);
        String jwtToken = jwtUtils.generateToken(email);
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        } else {
            return new UserResponse(user.getFirstName(),
                    user.getLastName(),
                    user.getUserName(),
                    user.getEmail(),
                    jwtToken);
        }
    }
}
