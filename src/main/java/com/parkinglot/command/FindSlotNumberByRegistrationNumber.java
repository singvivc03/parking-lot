package com.parkinglot.command;

import com.parkinglot.data.ParkingLotDataHolder;
import com.parkinglot.dto.Response;

public class FindSlotNumberByRegistrationNumber extends AbstractCommand {

  public FindSlotNumberByRegistrationNumber(final ParkingLotDataHolder parkingLotDao) {
    super(parkingLotDao);
  }

  @Override
  public Response execute(final String inputCommand) {
    validateAndThrowIfInvalidRequest(inputCommand, 1);
    var allBookedSlots = parkingLotDao.getAllBookedSlot();
    var optionalSlot = allBookedSlots.parallelStream()
            .filter(it -> inputCommand.equalsIgnoreCase(it.getParkedVehicle().getRegistrationNumber()))
            .findFirst();
    return optionalSlot.map(it -> new Response(String.valueOf(it.getId())))
            .orElse(new Response("No such vehicle"));
  }
}
