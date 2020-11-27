package com.parkinglot.command;

import com.parkinglot.data.ParkingLotDataHolder;
import com.parkinglot.dto.Vehicle;
import com.parkinglot.exceptions.InvalidSlotSelectionException;
import com.parkinglot.strategy.FillFirstParkingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnParkCommandTest {

  private UnParkCommand unParkCommand;

  @BeforeEach
  void beforeEach() {
    ParkingLotDataHolder parkingLotDataHolder = new ParkingLotDataHolder();
    parkingLotDataHolder.createParkingLot(1);
    parkingLotDataHolder.initializeParkingStrategy(new FillFirstParkingStrategy());
    parkingLotDataHolder.parkVehicle(new Vehicle("dummy", "White"));
    this.unParkCommand = new UnParkCommand(parkingLotDataHolder);
  }

  @ParameterizedTest
  @CsvSource({
     "1"
  })
  void shouldVerifyUnParkCommandEmptiesTheOccupiedSlot(final String inputRequest) {
    var response = this.unParkCommand.execute(inputRequest);
    assertThat(response.getMessage(), is("Slot number " + inputRequest + " is free"));
  }

  @Test
  void shouldThrowInvalidSlotSelectionException() {
    var exception = assertThrows(InvalidSlotSelectionException.class,
      () -> this.unParkCommand.execute("2"));
    assertThat(exception, instanceOf(InvalidSlotSelectionException.class));
    assertThat(exception.getMessage(), is("invalid slot id"));
  }
}
