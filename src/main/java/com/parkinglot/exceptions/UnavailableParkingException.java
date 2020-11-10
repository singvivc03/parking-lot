package com.parkinglot.exceptions;

public class UnavailableParkingException extends RuntimeException {

  public UnavailableParkingException(final String exception) {
    super(exception);
  }
}
