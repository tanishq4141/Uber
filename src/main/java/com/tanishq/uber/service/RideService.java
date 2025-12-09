package com.tanishq.uber.service;

import com.tanishq.uber.dto.RideRequestDTO;
import com.tanishq.uber.dto.RideResponseDTO;
import com.tanishq.uber.model.Ride;
import com.tanishq.uber.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RideService {

  @Autowired
  private RideRepository rideRepository;

  public RideResponseDTO createRide(RideRequestDTO request, String userId) {
    Ride ride = new Ride();
    ride.setUserId(userId);
    ride.setPickupLocation(request.getPickupLocation());
    ride.setDropLocation(request.getDropLocation());
    ride.setStatus("REQUESTED");
    ride.setCreatedAt(new Date());

    Ride savedRide = rideRepository.save(ride);
    return mapToDTO(savedRide);
  }

  public java.util.List<RideResponseDTO> getPendingRides() {
    return rideRepository.findByStatus("REQUESTED").stream()
        .map(this::mapToDTO)
        .collect(java.util.stream.Collectors.toList());
  }

  public RideResponseDTO acceptRide(String rideId, String driverId) {
    Ride ride = rideRepository.findById(rideId).orElseThrow(() -> new RuntimeException("Ride not found"));

    if (!"REQUESTED".equals(ride.getStatus())) {
      throw new RuntimeException("Ride is not available");
    }

    ride.setStatus("ACCEPTED");
    ride.setDriverId(driverId);
    Ride savedRide = rideRepository.save(ride);

    return mapToDTO(savedRide);
  }

  private RideResponseDTO mapToDTO(Ride ride) {
    return new RideResponseDTO(
        ride.getId(),
        ride.getStatus(),
        ride.getDriverId(),
        ride.getUserId(),
        ride.getPickupLocation(),
        ride.getDropLocation(),
        ride.getCreatedAt());
  }
}
