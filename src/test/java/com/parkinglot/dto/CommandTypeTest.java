package com.parkinglot.dto;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CommandTypeTest {

  @ParameterizedTest
  @CsvSource({
     "create_parking_lot, CREATE",
     "park, PARK",
     "leave, UNPARK",
     "status, STATUS",
     "registration_numbers_for_cars_with_colour, REGISTRATION_NUMBER_BY_COLOR",
     "slot_number_for_cars_with_registration_numbers, SLOT_BY_REGISTRATION_NUMBER",
     "slot_numbers_for_cars_with_colour, SLOT_NUMBER_BY_COLOR",
     "dispatch_rule, DISPATCH_RULE",
  })
  void shouldReturnCommandTypeForTheGivenValue(final String value, final String expectedCommand) {
    var actualCommandType = CommandType.getCommandTypeFromValue(value);
    assertThat(actualCommandType.name(), is(expectedCommand));
  }
}
