package com.example.restaurant.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingRequest {
    private String name;
    private LocalDateTime dateTime;
    private Integer guestCount;
    private Long userId;

    public BookingRequest() {}

    public BookingRequest(LocalDateTime dateTime, Integer guestCount, Long userId) {
        this.dateTime = dateTime;
        this.guestCount = guestCount;
        this.userId = userId;
    }

    public LocalDateTime getDate() {
        return dateTime;
    }

    public String getName() {
        return name;
    }

    public Integer getGuestCount() {
        return guestCount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setDate(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGuestCount(Integer guestCount) {
        this.guestCount = guestCount;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
