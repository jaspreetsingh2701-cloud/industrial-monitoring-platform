package com.monitoring.device.model;

// Represents a device managed by the Device Service.
public class Device {

  private Long id;
  private String name;
  private String type;

  // Creates a device using the supplied properties.
  public Device(Long id, String name, String type) {
    this.id = id;
    this.name = name;
    this.type = type;
  }

  // Provides access to the device properties.
  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  // Allows the service to update device information.
  public void setName(String name) {
    this.name = name;
  }

  public void setType(String type) {
    this.type = type;
  }
}
