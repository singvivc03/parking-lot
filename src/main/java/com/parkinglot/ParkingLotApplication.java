package com.parkinglot;

import com.parkinglot.dto.Response;
import com.parkinglot.handler.RequestHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static java.util.Optional.ofNullable;

public class ParkingLotApplication {

  public static void main(final String[] args) throws Exception {
    var inputFile = args.length > 0 ? args[0] : null;
    var requestHandler = new RequestHandler();
    var parkingLotApplication = new ParkingLotApplication();
    var bufferedReader = parkingLotApplication.getInputStreamToReadFrom(inputFile);
    bufferedReader.lines().takeWhile(line -> !"exit".equalsIgnoreCase(line))
            .map(requestHandler::handleRequest).map(Response::getMessage).forEach(System.out::println);
  }

  private BufferedReader getInputStreamToReadFrom(final String file) {
    var inputStream = ofNullable(file).map(this::getFileInputStream).orElse(System.in);
    return new BufferedReader(new InputStreamReader(inputStream));
  }

  private InputStream getFileInputStream(final String file) {
    var inputStream = ParkingLotApplication.class.getResourceAsStream(file);
    try {
      inputStream = ofNullable(inputStream).orElse(new FileInputStream(new File(file)));
    } catch (FileNotFoundException ex) {
      System.err.println(ex.getMessage());
    }
    return inputStream;
  }
}
