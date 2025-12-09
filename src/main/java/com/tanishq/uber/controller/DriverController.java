package com.tanishq.uber.controller;

import com.tanishq.uber.dto.RideResponseDTO;
import com.tanishq.uber.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/driver/rides")
public class DriverController {

  @Autowired
  private RideService rideService;

  @GetMapping("/requests")
  public ResponseEntity<List<RideResponseDTO>> getPendingRides() {
    // Enforce ROLE_DRIVER check here if SecurityConfig permits all
    // For now, assuming standard flow. Real security would be
    // @PreAuthorize("hasRole('DRIVER')")
    return ResponseEntity.ok(rideService.getPendingRides());
  }

  @PostMapping("/{rideId}/accept")
  public ResponseEntity<RideResponseDTO> acceptRide(@PathVariable String rideId, Principal principal) {
    // Get driver ID from principal or use a placeholder/header for simple testing
    String driverId = "anonymousDriver";
    if (principal != null) {
      driverId = principal.getName();
    }

    // In a real app, verify user has ROLE_DRIVER here if not done by security
    // filter.

    try {
      RideResponseDTO response = rideService.acceptRide(rideId, driverId);
      return ResponseEntity.ok(response);
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().build(); // Or specific error message
    }
  }
}
