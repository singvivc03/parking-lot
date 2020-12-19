package com.parkinglot.command;

import com.parkinglot.data.ParkingLotDataHolder;
import com.parkinglot.dto.Response;

import java.util.Optional;

import static java.util.stream.Collectors.joining;

public class FindSlotNumbersByVehicleColor extends AbstractCommand {

  public FindSlotNumbersByVehicleColor(final ParkingLotDataHolder parkingLotDao) {
    super(parkingLotDao);
  }

  @Override
  public Response execute(final String inputCommand) {
    validateAndThrowIfInvalidRequest(inputCommand, 1);
    var allBookedSlots = parkingLotDao.getAllBookedSlot();
    var allSlotsWithColor = allBookedSlots.parallelStream()
            .filter(it -> inputCommand.equalsIgnoreCase(it.getParkedVehicle().getColor()))
            .map(it -> String.valueOf(it.getId())).collect(joining(","));
    return new Response(Optional.of(allSlotsWithColor).filter(it -> !it.isEmpty()).orElse("No slots found"));
  }
}
