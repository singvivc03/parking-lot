package com.parkinglot.command;

import com.parkinglot.data.ParkingLotDataHolder;
import com.parkinglot.dto.Response;

import static java.util.stream.Collectors.joining;

public class FindRegistrationNumberByColorCommand extends AbstractCommand {

  public FindRegistrationNumberByColorCommand(ParkingLotDataHolder parkingLotDao) {
    super(parkingLotDao);
  }

  @Override
  public Response execute(final String inputCommand) {
    validateAndThrowIfInvalidRequest(inputCommand, 1);
    var allBookedSlots = parkingLotDao.getAllBookedSlot();
    var registrationNumber = allBookedSlots.parallelStream()
            .filter(it -> inputCommand.trim().equalsIgnoreCase(it.getParkedVehicle().getColor()))
            .map(it -> it.getParkedVehicle().getRegistrationNumber()).collect(joining(","));
    return new Response(registrationNumber);
  }
}
