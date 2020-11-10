package com.parkinglot.command;

import com.parkinglot.data.ParkingLotDataHolder;
import com.parkinglot.dto.Rule;
import com.parkinglot.exceptions.UnavailableParkingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingCommandTest {

  private ParkingCommand parkingCommand;

  @BeforeEach
  void beforeEach() {
    ParkingLotDataHolder parkingLotDataHolder = new ParkingLotDataHolder();
    parkingLotDataHolder.createParkingLot(1);
    parkingLotDataHolder.determineStrategyByRule(Rule.FILL_FIRST);
    parkingCommand = new ParkingCommand(parkingLotDataHolder);
  }

  @ParameterizedTest
  @CsvSource({
    "KA-01-HH-1234 White, 1",
    "KA-01-HH-9999 White, 1"
  })
  void shouldVerifyCarIsParkedAndIsGivenTheRequiredSlot(final String inputRequest, final int allottedSlot) {
    var response = parkingCommand.execute(inputRequest);
    String expectedResponse = "Allocated slot number: %d";
    assertThat(response.getMessage(), is(String.format(expectedResponse, allottedSlot)));
  }

  @Test
  void shouldEventuallyThrowUnavailableParkingException() {
    var inputRequest = "KA-01-HH-1234 White";
    var response = parkingCommand.execute(inputRequest);
    var anotherRequest = "KA-01-HH-9999 White";
    response = parkingCommand.execute(anotherRequest);
    assertThat(response.getMessage(), is("Sorry, parking lot is full"));
  }
}
