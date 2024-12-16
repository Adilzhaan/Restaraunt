package com.example.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="tables")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer number;

    @Column(nullable = false)
    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

}
