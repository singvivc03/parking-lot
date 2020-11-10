package com.parkinglot.command;

import com.parkinglot.data.ParkingLotDataHolder;
import com.parkinglot.dto.Response;
import com.parkinglot.dto.Vehicle;
import com.parkinglot.exceptions.UnavailableParkingException;

public class ParkingCommand extends AbstractCommand {

  public ParkingCommand(final ParkingLotDataHolder parkingLotDataHolder) {
    super(parkingLotDataHolder);
  }

  @Override
  public Response execute(final String inputCommand) {
    validateAndThrowIfInvalidRequest(inputCommand, 2);
    var inputs = inputCommand.split(" ");
    var vehicle = new Vehicle(inputs[0].trim(), inputs[1].trim());
    return parkVehicle(vehicle);
  }

  private Response parkVehicle(final Vehicle vehicle) {
    try {
      var allottedSlot = parkingLotDao.parkVehicle(vehicle);
      return new Response("Allocated slot number: " + allottedSlot.getId());
    } catch (UnavailableParkingException ex) {
      return new Response("Sorry, parking lot is full");
    }
  }
}
