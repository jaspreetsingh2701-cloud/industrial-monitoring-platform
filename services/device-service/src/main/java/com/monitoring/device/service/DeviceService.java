package com.monitoring.device.service;

import com.monitoring.device.exception.DuplicateDeviceException;
import com.monitoring.device.model.Device;
import com.monitoring.device.repository.DeviceRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

// Marks this class as a Spring-managed service component.
@Service
public class DeviceService {

  private final DeviceRepository deviceRepository;

  // Constructor injection: Spring supplies the DeviceRepository Bean.
  public DeviceService(DeviceRepository deviceRepository) {
    this.deviceRepository = deviceRepository;
  }

  // Retrieves all devices through the repository.
  public List<Device> getAllDevices() {
    return deviceRepository.findAll();
  }

  // Retrieves one device; Optional represents a possible missing result.
  public Optional<Device> getDeviceById(Long id) {
    return deviceRepository.findById(id);
  }

  // Creates a device only when its ID is valid and not already registered.
  public Device createDevice(Device device) {

    // A device must have an ID before it can be registered.
    if (device.getId() == null || device.getId() <= 0) {
      throw new IllegalArgumentException("A positive device ID is required");
    }

    // Prevent duplicate device IDs.
    if (deviceRepository.findById(device.getId()).isPresent()) {
      throw new DuplicateDeviceException("Device with ID " + device.getId() + " already exists");
    }

    // Save the device only after the checks pass.
    return deviceRepository.save(device);
  }

  // Updates an existing device, or returns empty when its ID is unknown.
  public Optional<Device> updateDevice(Long id, String name, String type) {
    Optional<Device> existingDevice = deviceRepository.findById(id);

    // Only update the device when it exists.
    existingDevice.ifPresent(device -> {
      device.setName(name);
      device.setType(type);
    });

    return existingDevice;
  }

  // Delegates deletion to the repository.
  public boolean deleteDevice(Long id) {
    return deviceRepository.deleteById(id);
  }
}
