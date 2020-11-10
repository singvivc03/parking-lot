package com.parkinglot.dto;

public class Response {

  private final String message;

  public Response(final String message) {
    this.message = message;
  }

  public String getMessage() {
    return this.message;
  }
}
