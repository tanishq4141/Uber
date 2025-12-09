package com.tanishq.uber.dto;

public class RideRequestDTO {
  private String pickupLocation;
  private String dropLocation;

  public RideRequestDTO() {
  }

  public RideRequestDTO(String pickupLocation, String dropLocation) {
    this.pickupLocation = pickupLocation;
    this.dropLocation = dropLocation;
  }

  public String getPickupLocation() {
    return pickupLocation;
  }

  public void setPickupLocation(String pickupLocation) {
    this.pickupLocation = pickupLocation;
  }

  public String getDropLocation() {
    return dropLocation;
  }

  public void setDropLocation(String dropLocation) {
    this.dropLocation = dropLocation;
  }
}
