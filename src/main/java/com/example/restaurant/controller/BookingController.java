package com.example.restaurant.controller;

import com.example.restaurant.dto.BookingRequest;
import com.example.restaurant.dto.BookingResponse;
import com.example.restaurant.entity.User;
import com.example.restaurant.service.BookingService;
import com.example.restaurant.util.AuthenticatedUserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    public BookingController(AuthenticatedUserProvider authenticatedUserProvider) {
        this.authenticatedUserProvider = authenticatedUserProvider;
    }

    @PostMapping("/create")
    public String createBooking(@RequestParam String name, @RequestParam String date) {
        // Assuming authenticated user is used
        User user = authenticatedUserProvider.getAuthenticatedUserProvider();

        // Create and save the booking
        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setName(name);
        bookingRequest.setDate(LocalDateTime.parse(date)); // Parse the date from the form

        bookingService.createBooking(bookingRequest, user);

        // Redirect to main page
        return "redirect:/bookings/main";
    }

    @PostMapping("/bookings")
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest bookingRequest) {
        User user = authenticatedUserProvider.getAuthenticatedUserProvider();
        BookingResponse response = bookingService.createBooking(bookingRequest, user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/main")
    public String showBookings(Model model) {
        List<BookingResponse> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "main";
    }
}
