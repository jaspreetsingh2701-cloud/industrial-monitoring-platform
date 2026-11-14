package com.monitoring.device.repository;

import com.monitoring.device.model.Device;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

// Marks this class as a Spring-managed repository component.
@Repository
public class DeviceRepository {

  // Temporary in-memory storage for devices.
  private final List<Device> devices = new ArrayList<>();

  // Returns all registered devices.
  public List<Device> findAll() {
    return new ArrayList<>(devices);
  }

  // Finds a device by its unique ID.
  public Optional<Device> findById(Long id) {
    return devices.stream().filter(device -> device.getId().equals(id)).findFirst();
  }

  // Adds a new device to storage.
  public Device save(Device device) {
    devices.add(device);
    return device;
  }

  // Removes a device from storage when it exists.
  public boolean deleteById(Long id) {
    return devices.removeIf(device -> device.getId().equals(id));
  }
}
