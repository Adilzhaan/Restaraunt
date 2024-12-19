package com.example.restaurant.controller;
import com.example.restaurant.entity.User;
import com.example.restaurant.enums.Role;
import com.example.restaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        if (user.getUsername() == null || user.getPassword() == null || user.getEmail() == null) {
            model.addAttribute("error", "All fields are required.");
            return "register";
        }
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole(Role.USER);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return "redirect:/login";
    }
}