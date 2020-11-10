package com.parkinglot.command;

import com.parkinglot.data.ParkingLotDataHolder;
import com.parkinglot.exceptions.InvalidRequestArgumentsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateParkingLotCommandTest {

  private CreateParkingLotCommand createParkingLotCommand;

  @BeforeEach
  void beforeEach() {
    createParkingLotCommand = new CreateParkingLotCommand(new ParkingLotDataHolder());
  }

  @ParameterizedTest
  @CsvSource({
    "1",
    "2",
    "10",
    "10000"
  })
  void testShouldVerifyNewParkingLotIsCreated(final String inputRequest) {
    var response = createParkingLotCommand.execute(inputRequest);
    String message = "Created a parking lot with %s slots";
    var expectedMessage = String.format(message, inputRequest);
    assertThat(response.getMessage(), is(expectedMessage));
  }

  @Test
  void shouldThrowInvalidRequestException() {
    var exception = assertThrows(InvalidRequestArgumentsException.class,
       () -> createParkingLotCommand.execute(null));
    assertThat(exception, instanceOf(InvalidRequestArgumentsException.class));
    assertThat(exception.getMessage(), is("Request object is not valid. Either one or more arguments is missing"));
  }
}
