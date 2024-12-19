package com.example.restaurant.repository;

import com.example.restaurant.entity.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {
    List<RestaurantTable> findByRestaurantId(Long restaurantId);
    RestaurantTable findByNumberAndRestaurantId(Integer number, Long restaurantId);
    @Query("SELECT t FROM RestaurantTable t WHERE t.capacity >= :guestCount AND t.isAvailable = TRUE")
    List<RestaurantTable> findAvailableTables(@Param("guestCount") Integer guestCount);}
