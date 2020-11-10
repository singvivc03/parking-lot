package com.parkinglot.strategy;

import com.parkinglot.dto.ParkingLot;

import java.util.List;
import java.util.Optional;

public class FillFirstParkingStrategy implements ParkingStrategy {

  @Override
  public Optional<ParkingLot> determineParkingLot(final List<ParkingLot> parkingLots) {
    return parkingLots.stream().filter(it -> !it.isParkingFul()).findFirst();
  }
}
