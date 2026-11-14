package com.monitoring.device.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

// Represents and validates the data received from API clients.
public class DeviceRequest {

  // ID must be positive when supplied; POST will require it in the service.
  @Positive(message = "Device ID must be a positive number")
  private Long id;

  // Device name cannot be null, empty or whitespace-only.
  @NotBlank(message = "Device name is required")
  private String name;

  // Device type cannot be null, empty or whitespace-only.
  @NotBlank(message = "Device type is required")
  private String type;

  // Supports JSON deserialization by the framework.
  public DeviceRequest() {}

  // Getters and setters expose properties to the JSON deserializer.
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
