package com.parkinglot.command;

import com.parkinglot.data.ParkingLotDataHolder;
import com.parkinglot.dto.Vehicle;
import com.parkinglot.strategy.FillFirstParkingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FindSlotNumbersByVehicleColorTest {

  private FindSlotNumbersByVehicleColor findSlotNumbersByVehicleColor;

  @BeforeEach
  void beforeEach() {
    ParkingLotDataHolder parkingLotDataHolder = new ParkingLotDataHolder();
    parkingLotDataHolder.createParkingLot(2);
    parkingLotDataHolder.initializeParkingStrategy(new FillFirstParkingStrategy());
    parkingLotDataHolder.parkVehicle(new Vehicle("dummy", "White"));
    parkingLotDataHolder.parkVehicle(new Vehicle("dummy2", "White"));
    findSlotNumbersByVehicleColor = new FindSlotNumbersByVehicleColor(parkingLotDataHolder);
  }

  @Test
  void shouldReturnSlotIdsHavingParkedVehicleInAGivenColor() {
    var response = findSlotNumbersByVehicleColor.execute("White");
    assertThat(response.getMessage(), is("1,2"));
  }

  @Test
  void shouldReturnEmptySlots() {
    var response = findSlotNumbersByVehicleColor.execute("Red");
    assertThat(response.getMessage(), is("No slots found"));
  }
}
