package com.parkinglot.dto;

import com.parkinglot.exceptions.UnavailableParkingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotDtoTest {

  @ParameterizedTest
  @CsvSource({
          "10",
          "1",
          "1000"
  })
  void shouldCreateParkingLotWithGivenCapacity(final int capacity) {
    var parkingLot = new ParkingLot(capacity);
    assertThat(parkingLot.getCapacity(), is(capacity));
  }

  @ParameterizedTest
  @CsvSource({
          "-1",
          "0",
          "-10"
  })
  void shouldThrowExceptionOnIllegalCapacity(final int capacity) {
    var exception = assertThrows(IllegalArgumentException.class, () -> new ParkingLot(capacity));
    assertThat(exception, instanceOf(IllegalArgumentException.class));
    assertThat(exception.getMessage(), is("invalid capacity " + capacity + " provided"));
  }

  @Test
  void shouldReturnAvailableSlot() {
    var parkingLot = new ParkingLot(1);
    var availableSlot = parkingLot.getAvailableSlot();
    assertThat(availableSlot, is(1));
  }

  @Test
  void shouldThrowUnavailableParkingException() {
    var parkingLot = new ParkingLot(1);
    var availableSlot = parkingLot.getAvailableSlot();
    assertThat(availableSlot, is(1));
    var exception = assertThrows(UnavailableParkingException.class,
      parkingLot::getAvailableSlot);
    assertThat(exception, instanceOf(UnavailableParkingException.class));
    assertThat(exception.getMessage(), is("Parking not available"));
  }
}
