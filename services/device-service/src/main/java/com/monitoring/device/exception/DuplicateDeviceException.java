package com.monitoring.device.exception;

// Represents an attempt to register a device ID that already exists.
public class DuplicateDeviceException extends RuntimeException {

  // Creates an exception with a meaningful error message.
  public DuplicateDeviceException(String message) {
    super(message);
  }
}
