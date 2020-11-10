package com.parkinglot.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class VehicleDtoTest {

  @DisplayName("Should verify Vehicle object is constructed with different parameters")
  @ParameterizedTest
  @CsvSource({
          "KA-01-HH-1234, White",
          "KA-01-HH-1111, Black",
          "DL-01-HH-1000, Blue",
          "MH-01-HH-1134, Red",
          "HR-01-HH-5000, Orange",
          "UP-01-HH-0007, Purple",
  })
  void shouldCreateVehicleWithRequiredDetail(final String registrationNumber, final String color) {
    var vehicle = new Vehicle(registrationNumber, color);
    assertThat(vehicle.getRegistrationNumber(), is(registrationNumber));
    assertThat(vehicle.getColor(), is(color));
  }
}
