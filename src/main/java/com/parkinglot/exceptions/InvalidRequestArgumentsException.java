package com.parkinglot.exceptions;

public class InvalidRequestArgumentsException extends RuntimeException {

  public InvalidRequestArgumentsException(final String errorMessage) {
    super(errorMessage);
  }
}
