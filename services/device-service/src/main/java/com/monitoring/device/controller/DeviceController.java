package com.monitoring.device.controller;

import com.monitoring.device.dto.DeviceRequest;
import com.monitoring.device.model.Device;
import com.monitoring.device.service.DeviceService;
// Import Bean Validation's request validation annotation.
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
// Import only the Spring MVC annotations used by this controller.
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// Marks this class as a REST controller managed by Spring.
@RestController
public class DeviceController {

  private final DeviceService deviceService;

  // Spring injects the DeviceService Bean through the constructor.
  public DeviceController(DeviceService deviceService) {
    this.deviceService = deviceService;
  }

  // GET /devices: returns all registered devices.
  @GetMapping("/devices")
  public List<Device> getDevices() {
    return deviceService.getAllDevices();
  }

  // GET /devices/{id}: returns one device or HTTP 404.
  @GetMapping("/devices/{id}")
  public ResponseEntity<Device> getDevice(@PathVariable("id") Long id) {
    return ResponseEntity.of(deviceService.getDeviceById(id));
  }

  // POST /devices: creates a device and returns HTTP 201.
  @PostMapping("/devices")
  public ResponseEntity<Device> createDevice(@Valid @RequestBody DeviceRequest request) {

    // Convert the incoming request DTO into the service's domain model.
    Device device = new Device(request.getId(), request.getName(), request.getType());

    // Ask the service to create the device.
    Device createdDevice = deviceService.createDevice(device);

    // Return the created resource and its URL.
    URI location = URI.create("/devices/" + createdDevice.getId());
    return ResponseEntity.created(location).body(createdDevice);
  }

  // PUT /devices/{id}: updates an existing device.
  @PutMapping("/devices/{id}")
  public ResponseEntity<Device> updateDevice(
      @PathVariable("id") Long id, @Valid @RequestBody DeviceRequest request) {

    // Return HTTP 200 if updated, or HTTP 404 if the device doesn't exist.
    return ResponseEntity.of(deviceService.updateDevice(id, request.getName(), request.getType()));
  }

  // DELETE /devices/{id}: deletes an existing device.
  @DeleteMapping("/devices/{id}")
  public ResponseEntity<Void> deleteDevice(@PathVariable("id") Long id) {

    // Return HTTP 204 when deleted, or HTTP 404 when no device was found.
    if (deviceService.deleteDevice(id)) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.notFound().build();
  }
}
