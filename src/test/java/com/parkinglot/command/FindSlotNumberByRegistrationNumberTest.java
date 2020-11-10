package com.parkinglot.command;

import com.parkinglot.data.ParkingLotDataHolder;
import com.parkinglot.dto.Rule;
import com.parkinglot.dto.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FindSlotNumberByRegistrationNumberTest {

  private FindSlotNumberByRegistrationNumber findSlotNumberByRegistrationNumber;

  @BeforeEach
  void beforeEach() {
    ParkingLotDataHolder parkingLotDataHolder = new ParkingLotDataHolder();
    parkingLotDataHolder.createParkingLot(2);
    parkingLotDataHolder.determineStrategyByRule(Rule.FILL_FIRST);
    parkingLotDataHolder.parkVehicle(new Vehicle("dummy", "White"));
    parkingLotDataHolder.parkVehicle(new Vehicle("dummy2", "White"));
    findSlotNumberByRegistrationNumber = new FindSlotNumberByRegistrationNumber(parkingLotDataHolder);
  }

  @ParameterizedTest
  @CsvSource({
          "dummy, 1",
          "dummy2, 2"
  })
  void shouldReturnSlotNumberForTheGivenRegistrationNumber(final String regNumber, final String expectedSlot) {
    var response = findSlotNumberByRegistrationNumber.execute(regNumber);
    assertThat(response.getMessage(), is(expectedSlot));
  }

  @Test
  void shouldReturnResponseHavingNoSuchVehicle() {
    var response = findSlotNumberByRegistrationNumber.execute("dummy3");
    assertThat(response.getMessage(), is("No such vehicle"));
  }
}
