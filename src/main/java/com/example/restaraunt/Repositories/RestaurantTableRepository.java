package com.example.restaraunt.Repositories;

import com.example.restaraunt.Entities.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {
    // Можно добавить методы для поиска столов по номеру или ресторану
    List<RestaurantTable> findByRestaurantId(Long restaurantId);
    RestaurantTable findByNumberAndRestaurantId(Integer number, Long restaurantId);
}
