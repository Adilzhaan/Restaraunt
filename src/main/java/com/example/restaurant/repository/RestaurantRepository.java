package com.example.restaurant.repository;

import com.example.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    // Можно добавить методы для поиска ресторанов по имени или местоположению
    List<Restaurant> findByLocation(String location);
    List<Restaurant> findByNameContaining(String name);
}
