package com.project.back_end.controllers;

import com.project.back_end.models.Doctor;
import com.project.back_end.services.DoctorService;
import com.project.back_end.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/{id}/availability")
    public ResponseEntity<?> getDoctorAvailability(
            @RequestHeader(value = "Authorization", required = false) String token,
            @PathVariable Long id,
            @RequestParam String date) {

        if (token == null || !token.startsWith("Bearer ") || !tokenService.validateToken(token.substring(7))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid or missing token"));
        }

        List<String> availableSlots = doctorService.getAvailableSlots(id, date);
        if (availableSlots == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Doctor not found"));
        }

        return ResponseEntity.ok(Map.of(
            "doctorId", id,
            "date", date,
            "availableSlots", availableSlots
        ));
    }
}
