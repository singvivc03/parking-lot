package com.parkinglot.command;

import com.parkinglot.data.ParkingLotDataHolder;
import com.parkinglot.exceptions.InvalidRequestArgumentsException;

public abstract class AbstractCommand implements Command {

  protected ParkingLotDataHolder parkingLotDao;

  protected AbstractCommand(final ParkingLotDataHolder parkingLotDao) {
    this.parkingLotDao = parkingLotDao;
  }

  protected boolean validateAndThrowIfInvalidRequest(String request, int expectedArguments) {
    if(request == null || request.split(" ").length < expectedArguments) {
      throw new InvalidRequestArgumentsException("Request object is not valid. Either one or more arguments is missing");
    }
    return true;
  }
}
