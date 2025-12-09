package com.tanishq.uber.controller;

import com.tanishq.uber.dto.Login;
import com.tanishq.uber.dto.Signup;
import com.tanishq.uber.model.User;
import com.tanishq.uber.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody Signup request) {
        // Check if username already exists
        if (userRepository.findByUsername(request.getUsername()) != null) {
            return "User already exists!";
        }

        // Encode password before saving
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User(
                request.getUsername(),
                encodedPassword,
                request.getRole());

        userRepository.save(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public String login(@RequestBody Login request) {
        User user = userRepository.findByUsername(request.getUsername());

        if (user == null) {
            return "User not found!";
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return "Invalid password!";
        }

        return "Login successful!";
    }
}
