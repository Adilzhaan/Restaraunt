package com.example.restaurant.service;

import com.example.restaurant.dto.BookingRequest;
import com.example.restaurant.dto.BookingResponse;
import com.example.restaurant.entity.Booking;
import com.example.restaurant.entity.RestaurantTable;
import com.example.restaurant.entity.User;
import com.example.restaurant.enums.BookingStatus;
import com.example.restaurant.repository.BookingRepository;
import com.example.restaurant.repository.RestaurantTableRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService<BookingResponse> {
    @Autowired
    private RestaurantTableRepository tableRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public com.example.restaurant.dto.BookingResponse createBooking(BookingRequest request, User user) {
        List<RestaurantTable> tables = tableRepository.findAvailableTables(
                request.getGuestCount()
        );

        if (tables.isEmpty()) {
            throw new IllegalStateException("No available tables for the requested time and guest count");
        }

        RestaurantTable selectedTable = tables.getFirst();

        Booking booking = new Booking();
        booking.setDate(request.getDate());
        booking.setGuestCount(request.getGuestCount());
        booking.setUser(user);
        booking.setTable(selectedTable);
        booking.setStatus(BookingStatus.CONFIRMED);

        bookingRepository.save(booking);

        return new com.example.restaurant.dto.BookingResponse("Booking confirmed", booking);
    }

    public List<Booking> getUserBookings(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }


}
