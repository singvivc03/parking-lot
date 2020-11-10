package com.parkinglot.command;

import com.parkinglot.data.ParkingLotDataHolder;
import com.parkinglot.dto.Response;

import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

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
            .map(it -> it.getId()).collect(toList());
    StringBuilder slotBuilder = new StringBuilder();
    allSlotsWithColor.forEach(slotId -> slotBuilder.append(slotId + ","));
    int index = slotBuilder.lastIndexOf(",");
    if (index != -1) {
      var slots = slotBuilder.substring(0, slotBuilder.lastIndexOf(","));
      return new Response(slots);
    }
    return new Response("No slots found");
  }
}
