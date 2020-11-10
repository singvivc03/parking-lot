package com.parkinglot.exceptions;

public class InvalidSlotSelectionException extends RuntimeException {

  public InvalidSlotSelectionException(final String errorMessage) {
    super(errorMessage);
  }
}
