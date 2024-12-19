package com.example.restaurant.dto;

import com.example.restaurant.entity.Booking;

import java.time.LocalDate;

public class BookingResponse {
    private String message;
    private Booking booking;
    private Long id;      // Add this
    private String name;  // Add this
    private LocalDate date; // Add this (or the appropriate type for date)

    public BookingResponse() {
    }

    public BookingResponse(String message, Booking booking) {
        this.message = message;
        this.booking = booking;
    }

    // Getters and setters for the new fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}

