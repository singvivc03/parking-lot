package com.parkinglot.dto;

public class Vehicle {

  private final String registrationNumber;
  private final String color;

  public Vehicle(final String registrationNumber, final String color) {
    this.registrationNumber = registrationNumber;
    this.color = color;
  }

  public String getRegistrationNumber() {
    return this.registrationNumber;
  }

  public String getColor() {
    return this.color;
  }
}
