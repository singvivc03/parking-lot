package com.parkinglot.handler;

import com.parkinglot.command.Command;
import com.parkinglot.command.CreateParkingLotCommand;
import com.parkinglot.command.DispatcherCommand;
import com.parkinglot.command.FindRegistrationNumberByColorCommand;
import com.parkinglot.command.FindSlotNumberByRegistrationNumber;
import com.parkinglot.command.FindSlotNumbersByVehicleColor;
import com.parkinglot.command.ParkingCommand;
import com.parkinglot.command.StatusCommand;
import com.parkinglot.command.UnParkCommand;
import com.parkinglot.data.ParkingLotDataHolder;
import com.parkinglot.dto.CommandType;
import com.parkinglot.dto.Response;

import java.util.Map;

import static java.util.Optional.ofNullable;

public class RequestHandler {

  private final Map<CommandType, Command> commandByCommandType;

  public RequestHandler() {
    var parkingLotDataHolder = new ParkingLotDataHolder();
    commandByCommandType = Map.of(
            CommandType.CREATE, new CreateParkingLotCommand(parkingLotDataHolder),
            CommandType.PARK, new ParkingCommand(parkingLotDataHolder),
            CommandType.UNPARK, new UnParkCommand(parkingLotDataHolder),
            CommandType.STATUS, new StatusCommand(parkingLotDataHolder),
            CommandType.REGISTRATION_NUMBER_BY_COLOR, new FindRegistrationNumberByColorCommand(parkingLotDataHolder),
            CommandType.SLOT_BY_REGISTRATION_NUMBER, new FindSlotNumberByRegistrationNumber(parkingLotDataHolder),
            CommandType.SLOT_NUMBER_BY_COLOR, new FindSlotNumbersByVehicleColor(parkingLotDataHolder),
            CommandType.DISPATCH_RULE, new DispatcherCommand(parkingLotDataHolder));
  }

  public Response handleRequest(final String inputRequest) {
    var request = ofNullable(inputRequest).orElseThrow(() -> new IllegalArgumentException("Invalid request"));
    var tokens = request.split(" ");
    request = request.substring(tokens[0].length()).trim();
    var commandType = CommandType.getCommandTypeFromValue(tokens[0].trim());
    var command = commandByCommandType.get(commandType);
    return command.execute(request);
  }
}
