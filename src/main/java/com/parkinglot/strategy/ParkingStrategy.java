package com.parkinglot.strategy;

import com.parkinglot.dto.ParkingLot;
import com.parkinglot.dto.SlotDto;

import java.util.List;
import java.util.Optional;

public interface ParkingStrategy {

  Optional<ParkingLot> determineParkingLot(final List<ParkingLot> parkingLots);
}
