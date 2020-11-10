package com.parkinglot.handler;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RequestHandlerTest {

  private RequestHandler requestHandler;

  @BeforeEach
  void beforeEach() {
    this.requestHandler = new RequestHandler();
    requestHandler.handleRequest("create_parking_lot 1");
    requestHandler.handleRequest("dispatch_rule even_distribution");
  }

  @Test
  void shouldReturnWithSuccessfulVehiclePark() {
    String command = "park KA-01-HH-1234 White";
    var response = requestHandler.handleRequest(command);
    MatcherAssert.assertThat(response.getMessage(), Matchers.is("Allocated slot number: 1"));
  }

  @Test
  void shouldReturnWithSuccessfulVehicleUnPark() {
    String command = "park KA-01-HH-1234 White";
    var response = requestHandler.handleRequest(command);
    command = "leave 1";
    response = requestHandler.handleRequest(command);
    MatcherAssert.assertThat(response.getMessage(), Matchers.is("Slot number 1 is free"));
  }

  @Test
  void shouldReturnTheRegistrationNumberOfVehicleForTheGivenColor() {
    String command = "park KA-01-HH-1234 White";
    var response = requestHandler.handleRequest(command);
    command = "registration_numbers_for_cars_with_colour White";
    response = requestHandler.handleRequest(command);
    MatcherAssert.assertThat(response.getMessage(), Matchers.is("KA-01-HH-1234"));
  }
}
