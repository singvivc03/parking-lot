package com.parkinglot.command;

import com.parkinglot.data.ParkingLotDataHolder;
import com.parkinglot.dto.Response;
import com.parkinglot.dto.Rule;

public class DispatcherCommand extends AbstractCommand {

  public DispatcherCommand(final ParkingLotDataHolder parkingLotDataHolder) {
    super(parkingLotDataHolder);
  }

  @Override
  public Response execute(final String inputCommand) {
    validateAndThrowIfInvalidRequest(inputCommand, 1);
    var rule = Rule.getRuleByValue(inputCommand);
    parkingLotDao.determineStrategyByRule(rule);
    return new Response("Dispatcher is now using the " + rule.getDescription() + " rule");
  }
}
