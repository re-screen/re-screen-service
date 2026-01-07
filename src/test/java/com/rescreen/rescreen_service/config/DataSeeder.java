package com.rescreen.rescreen_service.config;


import com.rescreen.rescreen_service.security.model.Role;
import com.rescreen.rescreen_service.security.model.User;
import com.rescreen.rescreen_service.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository; // your JPA repository

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByEmail("test@example.com")) {
            User user = new User();
            user.setEmail("test@example.com");
            user.setRole(Role.valueOf("USER"));
            user.setEnabled(true);

            // Encode password123
            user.setPassword(passwordEncoder.encode("password123"));

            userRepository.save(user);
            System.out.println("User created with password123");
        }
    }
}

