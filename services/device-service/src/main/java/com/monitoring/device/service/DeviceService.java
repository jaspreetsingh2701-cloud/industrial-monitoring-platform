package com.monitoring.device.service;

import com.monitoring.device.exception.DuplicateDeviceException;
import com.monitoring.device.model.Device;
import com.monitoring.device.repository.DeviceRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

// Import the SLF4J logging API.

// Marks this class as a Spring-managed service component.
@Service
public class DeviceService {

  private final DeviceRepository deviceRepository;

  // Create a logger associated with this class.
  private static final Logger logger = LoggerFactory.getLogger(DeviceService.class);

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

    // Log a normal business event.
    logger.info("Creating device with ID: {}", device.getId());

    // Log detailed information useful during debugging.
    logger.debug("Device name: {}", device.getName());

    // Log an operation failure, including its exception.
    // logger.error("Failed to create device with ID: {}", device.getId(), exception);

    // A device must have an ID before it can be registered.
    if (device.getId() == null || device.getId() <= 0) {
      throw new IllegalArgumentException("A positive device ID is required");
    }

    // Prevent duplicate device IDs.
    if (deviceRepository.findById(device.getId()).isPresent()) {

      // Log the duplicate ID conflict.
      logger.warn("Cannot create device. ID {} already exists", device.getId());

      throw new DuplicateDeviceException("Device with ID " + device.getId() + " already exists");
    }

    // Save the device only after the checks pass.
    return deviceRepository.save(device);
  }

  // Updates an existing device, or returns empty when its ID is unknown.
  public Optional<Device> updateDevice(Long id, String name, String type) {
    Optional<Device> existingDevice = deviceRepository.findById(id);

    // Log the update operation.
    logger.info("Updating device with ID: {}", id);

    // Only update the device when it exists.
    existingDevice.ifPresent(device -> {
      device.setName(name);
      device.setType(type);
    });

    return existingDevice;
  }

  // Delegates deletion to the repository.
  public boolean deleteDevice(Long id) {
    // Log the deletion request.
    logger.info("Deleting device with ID: {}", id);
    return deviceRepository.deleteById(id);
  }
}
