package com.project.back_end.services;

import com.project.back_end.models.Doctor;
import com.project.back_end.repo.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TokenService tokenService;

    // Returns available time slots for doctor on a given date
    public List<String> getAvailableSlots(Long doctorId, String date) {
        Optional<Doctor> doctorOpt = doctorRepository.findById(doctorId);
        if (doctorOpt.isPresent()) {
            return doctorOpt.get().getAvailableTimes();
        }
        return null;
    }

    // Validates doctor login credentials and returns a structured response
    public Map<String, Object> validateDoctorLogin(String email, String password) {
        Map<String, Object> response = new HashMap<>();
        Optional<Doctor> doctorOpt = doctorRepository.findByEmail(email);

        if (doctorOpt.isPresent() && doctorOpt.get().getPassword().equals(password)) {
            Doctor doctor = doctorOpt.get();
            String token = tokenService.generateToken(doctor.getEmail());
            response.put("status", "success");
            response.put("token", token);
            response.put("doctor", doctor);
        } else {
            response.put("status", "error");
            response.put("message", "Invalid email or password");
        }
        return response;
    }
}
