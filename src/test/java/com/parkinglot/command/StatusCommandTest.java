package com.parkinglot.command;

import com.parkinglot.data.ParkingLotDataHolder;
import com.parkinglot.dto.Rule;
import com.parkinglot.dto.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StatusCommandTest {

  private StatusCommand statusCommand;

  @BeforeEach
  void beforeEach() {
    ParkingLotDataHolder parkingLotDataHolder = new ParkingLotDataHolder();
    parkingLotDataHolder.createParkingLot(1);
    parkingLotDataHolder.determineStrategyByRule(Rule.FILL_FIRST);
    parkingLotDataHolder.parkVehicle(new Vehicle("dummy", "Red"));
    statusCommand = new StatusCommand(parkingLotDataHolder);
  }

  @Test
  void shouldReturnParkingStatus() {
    var response = statusCommand.execute("");
    assertThat(response.getMessage(), is("Slot No.       Registration No       Color\n" +
            "1              dummy         Red\n"));
  }
}
