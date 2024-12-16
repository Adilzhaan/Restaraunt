package com.example.restaurant.repository;

import com.example.restaurant.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Можно добавить методы для поиска бронирований по пользователю или столу
    List<Booking> findByUserId(Long userId);
    List<Booking> findByTableId(Long tableId);
    List<Booking> findByStatus(String status);  // Пример для поиска по статусу
}
