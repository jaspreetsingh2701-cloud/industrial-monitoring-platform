package com.monitoring.device.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.monitoring.device.exception.DuplicateDeviceException;
import com.monitoring.device.model.Device;
import com.monitoring.device.service.DeviceService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

// Loads only the Spring MVC testing slice and the DeviceController.
@WebMvcTest(DeviceController.class)
class DeviceControllerTest {

  // Spring injects the configured MockMvc test client.
  @Autowired private MockMvc mockMvc;

  // Replaces the real service with a Mockito mock.
  @MockitoBean private DeviceService deviceService;

  // Verifies that GET returns the device list as JSON.
  @Test
  void shouldReturnAllDevices() throws Exception {
    Device device = new Device(101L, "Temperature Sensor", "TEMPERATURE");

    // Configure the mock service response.
    when(deviceService.getAllDevices()).thenReturn(List.of(device));

    mockMvc
        .perform(get("/devices"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(101))
        .andExpect(jsonPath("$[0].name").value("Temperature Sensor"))
        .andExpect(jsonPath("$[0].type").value("TEMPERATURE"));
  }

  // Verifies that POST creates a device and returns HTTP 201.
  @Test
  void shouldCreateDevice() throws Exception {

    // Return the device passed by the controller to the mock service.
    when(deviceService.createDevice(any(Device.class)))
        .thenAnswer(invocation -> invocation.getArgument(0));

    mockMvc
        .perform(
            post("/devices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                        "id": 101,
                        "name": "Temperature Sensor",
                        "type": "TEMPERATURE"
                    }
                    """))
        .andExpect(status().isCreated())
        .andExpect(header().string("Location", "/devices/101"))
        .andExpect(jsonPath("$.id").value(101))
        .andExpect(jsonPath("$.name").value("Temperature Sensor"));
  }

  // Verifies that invalid input returns HTTP 400 without calling the service.
  @Test
  void shouldRejectInvalidDeviceRequest() throws Exception {
    mockMvc
        .perform(
            post("/devices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                        "id": 102,
                        "name": "",
                        "type": "PRESSURE"
                    }
                    """))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.name").value("Device name is required"));

    // Validation should reject the request before service execution.
    verify(deviceService, never()).createDevice(any(Device.class));
  }

  // Verifies that duplicate IDs produce HTTP 409 Conflict.
  @Test
  void shouldReturnConflictForDuplicateDevice() throws Exception {

    // Simulate the service detecting an existing device ID.
    when(deviceService.createDevice(any(Device.class)))
        .thenThrow(new DuplicateDeviceException("Device with ID 101 already exists"));

    mockMvc
        .perform(
            post("/devices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                        "id": 101,
                        "name": "Duplicate Sensor",
                        "type": "TEMPERATURE"
                    }
                    """))
        .andExpect(status().isConflict())
        .andExpect(jsonPath("$.error").value("Device with ID 101 already exists"));
  }
}
