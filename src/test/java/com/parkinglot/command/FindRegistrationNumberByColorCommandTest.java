package com.parkinglot.command;

import com.parkinglot.data.ParkingLotDataHolder;
import com.parkinglot.dto.Vehicle;
import com.parkinglot.strategy.FillFirstParkingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FindRegistrationNumberByColorCommandTest {

  private FindRegistrationNumberByColorCommand findRegistrationNumberByColorCommand;

  @BeforeEach
  void beforeEach() {
    ParkingLotDataHolder parkingLotDataHolder = new ParkingLotDataHolder();
    parkingLotDataHolder.createParkingLot(2);
    parkingLotDataHolder.initializeParkingStrategy(new FillFirstParkingStrategy());
    parkingLotDataHolder.parkVehicle(new Vehicle("dummy", "White"));
    parkingLotDataHolder.parkVehicle(new Vehicle("dummy2", "White"));
    findRegistrationNumberByColorCommand = new FindRegistrationNumberByColorCommand(parkingLotDataHolder);
  }

  @Test
  void shouldReturnRegistrationForTheGivenColor() {
    var response = findRegistrationNumberByColorCommand.execute("White");
    assertThat(response.getMessage(), is("dummy,dummy2"));
  }
}
