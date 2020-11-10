package com.parkinglot.command;

import com.parkinglot.data.ParkingLotDataHolder;
import com.parkinglot.dto.Response;

public class CreateParkingLotCommand extends AbstractCommand {

  public CreateParkingLotCommand(final ParkingLotDataHolder parkingLotDataHolder) {
    super(parkingLotDataHolder);
  }

  @Override
  public Response execute(final String inputRequest) {
    validateAndThrowIfInvalidRequest(inputRequest, 1);
    var capacity = Integer.parseInt(inputRequest);
    parkingLotDao.createParkingLot(capacity);
    return new Response("Created a parking lot with " + capacity + " slots");
  }
}
