package com.example.restaraunt.Repositories;

import com.example.restaraunt.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Можно добавить метод для поиска пользователя по email
    User findByEmail(String email);
}
