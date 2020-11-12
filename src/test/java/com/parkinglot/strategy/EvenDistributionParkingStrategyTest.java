package com.parkinglot.strategy;

import com.parkinglot.dto.ParkingLot;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EvenDistributionParkingStrategyTest {

  private final ParkingStrategy parkingStrategy = new EvenDistributionParkingStrategy();

  @ParameterizedTest
  @CsvSource({
          "8;20;1, 20",
          "1;2;1, 2",
          "100;128;189;1000, 1000",
          "500;200;1, 500",
  })
  void shouldReturnParkingLotWithMaximumNumberOfFreeSlotsAvailable(final String cap, final int expectedAnswer) {
    var parkingLots = Arrays.asList(cap.split(";")).parallelStream()
            .map(it -> new ParkingLot(Integer.parseInt(it))).collect(toList());
    var optionalParkingLot = parkingStrategy.determineParkingLot(parkingLots);
    assertThat(optionalParkingLot.isEmpty(), is(false));
    assertThat(optionalParkingLot.get().getNumberOfAvailableFreeSlots(), is(expectedAnswer));
  }
}
