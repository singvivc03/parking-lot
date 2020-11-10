package com.parkinglot.strategy;

import com.parkinglot.dto.ParkingLot;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class EvenDistributionParkingStrategy implements ParkingStrategy {

  @Override
  public Optional<ParkingLot> determineParkingLot(final List<ParkingLot> parkingLots) {
    return parkingLots.stream().max(Comparator.comparing(ParkingLot::getNumberOfAvailableFreeSlots));
  }
}
