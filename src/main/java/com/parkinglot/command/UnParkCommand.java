package com.parkinglot.command;

import com.parkinglot.data.ParkingLotDataHolder;
import com.parkinglot.dto.Response;

import static java.util.Optional.ofNullable;

public class UnParkCommand extends AbstractCommand {

  public UnParkCommand(final ParkingLotDataHolder parkingLotDao) {
    super(parkingLotDao);
  }

  @Override
  public Response execute(final String inputCommand) {
    validateAndThrowIfInvalidRequest(inputCommand, 1);
    int slot = Integer.parseInt(inputCommand.trim());
    var vehicle = parkingLotDao.unPark(slot);
    return ofNullable(vehicle).map(it -> new Response(String.format("Slot number %d is free", slot)))
            .orElse(new Response("Invalid slot number"));
  }
}
