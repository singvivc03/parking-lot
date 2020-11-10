package com.parkinglot.dto;

import java.util.Arrays;

public enum CommandType {

  CREATE ("create_parking_lot"),
  PARK ("park"),
  UNPARK ("leave"),
  STATUS ("status"),
  REGISTRATION_NUMBER_BY_COLOR ("registration_numbers_for_cars_with_colour"),
  SLOT_BY_REGISTRATION_NUMBER ("slot_number_for_cars_with_registration_numbers"),
  SLOT_NUMBER_BY_COLOR ("slot_numbers_for_cars_with_colour"),
  DISPATCH_RULE ("dispatch_rule");

  private final String value;

  CommandType(final String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static CommandType getCommandTypeFromValue(final String value) {
    var optionalCommand = Arrays.asList(CommandType.values()).parallelStream()
            .filter(it -> value.equalsIgnoreCase(it.getValue())).findFirst();
    return optionalCommand.orElseThrow(() -> new IllegalArgumentException("Invalid command"));
  }
}
