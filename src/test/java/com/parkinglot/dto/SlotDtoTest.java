package com.parkinglot.dto;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SlotDtoTest {

  @ParameterizedTest
  @CsvSource({
          "1, 1, KA-01-HH-1234, White",
          "2, 1, KA-01-HH-9999, White",
          "2, 1, KA-01-HH-3141, Black",
          "2, 1, KA-01-HH-9999, Blue",
          "2, 1, KA-01-HH-333, Red",
  })
  void shouldVerifyCreatedSlotDtoIsValid(final int slotId, final String parkingId, final String regNumber,
                                         final String color) {
    var slotDto = new SlotDto(slotId, parkingId, new Vehicle(regNumber, color));
    assertThat(slotDto.getId(), is(slotId));
    assertThat(slotDto.getParkingId(), is(parkingId));
    assertThat(slotDto.getParkedVehicle().getRegistrationNumber(), is(regNumber));
    assertThat(slotDto.getParkedVehicle().getColor(), is(color));
  }
}
