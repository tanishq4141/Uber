package com.tanishq.uber.dto;

import java.util.Date;

public class RideResponseDTO {
  private String id;
  private String status;
  private String driverId;
  private String userId;
  private String pickupLocation;
  private String dropLocation;
  private Date createdAt;

  public RideResponseDTO() {
  }

  public RideResponseDTO(String id, String status, String driverId, String userId, String pickupLocation,
      String dropLocation, Date createdAt) {
    this.id = id;
    this.status = status;
    this.driverId = driverId;
    this.userId = userId;
    this.pickupLocation = pickupLocation;
    this.dropLocation = dropLocation;
    this.createdAt = createdAt;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getDriverId() {
    return driverId;
  }

  public void setDriverId(String driverId) {
    this.driverId = driverId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
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

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
}
