package com.example.restaraunt.Services;

import com.example.restaraunt.Entities.User;
import com.example.restaraunt.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Регистрация пользователя
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    // Поиск пользователя по email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
