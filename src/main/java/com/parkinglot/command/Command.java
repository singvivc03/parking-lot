package com.parkinglot.command;

import com.parkinglot.dto.Response;

public interface Command {

  Response execute(final String inputCommand);
}
