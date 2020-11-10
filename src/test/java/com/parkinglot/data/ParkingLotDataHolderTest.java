package com.parkinglot.data;

import com.parkinglot.dto.Rule;
import com.parkinglot.dto.Vehicle;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ParkingLotDataHolderTest {

  @Test
  void shouldCreateParkingLotWithTheGivenCapacity() {
    var parkingLotDataHolder = createDummyParkingLot();
    assertThat(parkingLotDataHolder.getParkingLots(), hasSize(1));
    assertThat(parkingLotDataHolder.getParkingLots().get(0).getCapacity(), is(1));
  }

  @Test
  void shouldVerifyVehicleIsAssignedASlot() {
    var parkingLotDataHolder = createDummyParkingLot();
    var allBookedSlot = parkingLotDataHolder.getAllBookedSlot();
    assertThat(allBookedSlot, hasSize(0));
    var slot = parkingLotDataHolder.parkVehicle(new Vehicle("test", "White"));
    var allSlots = parkingLotDataHolder.getAllBookedSlot().parallelStream().collect(Collectors.toList());
    assertThat(allSlots, hasSize(1));
    assertThat(allSlots.get(0).getId(), is(slot.getId()));
    assertThat(allSlots.get(0).getParkingId(), is(slot.getParkingId()));
    assertThat(allSlots.get(0).getParkedVehicle().getRegistrationNumber(),
            is(slot.getParkedVehicle().getRegistrationNumber()));
    assertThat(allSlots.get(0).getParkedVehicle().getColor(), is(slot.getParkedVehicle().getColor()));
  }

  @Test
  void shouldVerifyVehicleIsRemovedFromTheAssignedSlot() {
    var parkingLotDataHolder = createDummyParkingLot();
    var slot = parkingLotDataHolder.parkVehicle(new Vehicle("test", "White"));
    var vehicle = parkingLotDataHolder.unPark(slot.getId());
    assertThat(vehicle.getRegistrationNumber(), is("test"));
    assertThat(vehicle.getColor(), is("White"));
  }

  private ParkingLotDataHolder createDummyParkingLot() {
    var parkingLotDataHolder = new ParkingLotDataHolder();
    var ok = parkingLotDataHolder.createParkingLot(1);
    parkingLotDataHolder.determineStrategyByRule(Rule.FILL_FIRST);
    assertThat(ok, is(true));
    return parkingLotDataHolder;
  }
}
