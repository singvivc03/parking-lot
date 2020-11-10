package com.parkinglot.command;

import com.parkinglot.data.ParkingLotDataHolder;
import com.parkinglot.dto.Response;

public class StatusCommand extends AbstractCommand {

  public StatusCommand(final ParkingLotDataHolder parkingLotDao) {
    super(parkingLotDao);
  }

  @Override
  public Response execute(final String inputCommand) {
    var status = parkingLotDao.getParkingStatus();
    return new Response(status);
  }
}
