package com.parkinglot.strategy;

import com.parkinglot.command.ParkingCommand;
import com.parkinglot.data.ParkingLotDataHolder;
import com.parkinglot.dto.ParkingLot;
import com.parkinglot.dto.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FillFirstParkingStrategyTest {

  private final ParkingStrategy parkingStrategy = new FillFirstParkingStrategy();

  @ParameterizedTest
  @CsvSource({
          "8;20;1, 8",
          "1;2;1, 1",
          "100;128;189;1000, 100",
          "500;200;1, 500",
  })
  void shouldReturnNearestParkingLotWithSpace(final String cap, final int expectedAnswer) {
    var parkingLots = Arrays.asList(cap.split(";")).stream()
            .map(it -> new ParkingLot(Integer.parseInt(it))).collect(toList());
    var optionalParkingLot = parkingStrategy.determineParkingLot(parkingLots);
    assertThat(optionalParkingLot.isEmpty(), is(false));
    assertThat(optionalParkingLot.get().getNumberOfAvailableFreeSlots(), is(expectedAnswer));
  }

  @Test
  void shouldReturnEmptyOptionalParkingLot() {
    ParkingLotDataHolder parkingLotDataHolder = new ParkingLotDataHolder();
    parkingLotDataHolder.createParkingLot(1);
    parkingLotDataHolder.initializeParkingStrategy(new FillFirstParkingStrategy());
    var command = new ParkingCommand(parkingLotDataHolder);
    command.execute("dummy white");
    var optionalParkingLot = parkingStrategy.determineParkingLot(parkingLotDataHolder.getParkingLots());
    assertThat(optionalParkingLot.isEmpty(), is(true));
  }
}
