package com.parkinglot.dto;

import java.util.Objects;

public class SlotDto {

  private final int id;
  private final String parkingId;
  private final Vehicle parkedVehicle;

  public SlotDto(final int id, final String parkingId, final Vehicle vehicle) {
    this.id = id;
    this.parkingId = parkingId;
    this.parkedVehicle = vehicle;
  }

  public int getId() {
    return id;
  }

  public String getParkingId() {
    return parkingId;
  }

  public Vehicle getParkedVehicle() {
    return parkedVehicle;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public boolean equals(Object that) {
    return Objects.equals(this, that);
  }
}
