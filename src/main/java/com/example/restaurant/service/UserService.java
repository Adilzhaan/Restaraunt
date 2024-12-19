package com.example.restaurant.service;

import com.example.restaurant.entity.User;
import com.example.restaurant.repository.UserRepository;
import com.example.restaurant.util.AuthenticatedUserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    private final Map<String, String> resetTokenStore = new HashMap<>();
    @Autowired
    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder, EmailService emailService, AuthenticatedUserProvider authenticatedUserProvider) {
        this.userRepository = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.authenticatedUserProvider = authenticatedUserProvider;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public String generateResetTokenForUser(User user) {
        String token = UUID.randomUUID().toString();
        resetTokenStore.put(token, user.getEmail());
        return token;
    }

    public boolean isResetTokenValid(String token) {
        return resetTokenStore.containsKey(token);
    }

    public void resetPassword(String token, String newPassword) {
        String email = resetTokenStore.get(token);
        User user = findByEmail(email);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        resetTokenStore.remove(token);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void updateUserProfile(String username, String email, MultipartFile photo) {
        User user = authenticatedUserProvider.getAuthenticatedUserProvider();
        user.setUsername(username);
        user.setEmail(email);
        userRepository.save(user);
    }

    public void changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public void resetPassword(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("No user found with email: " + email));

        String newPassword = generateRandomPassword();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        emailService.sendEmail(email, "Password Reset",
                "Your new password is: " + newPassword);
    }

    private String generateRandomPassword() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

}
