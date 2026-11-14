import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeviceService {

  private final List<Device> devices = new ArrayList<>();

  // Register a new device
  public void register(Device device) {
    devices.add(device);
  }

  // Find device using ID
  public Optional<Device> findById(int id) {
    return devices.stream().filter(device -> device.getId() == id).findFirst();
  }

  // Return only active devices
  public List<Device> findActiveDevices() {
    return devices.stream()
        .filter(Device::isActive) // equivalent to .filter(device -> device.isActive())
        .toList();
  }

  // Total registered devices
  public int totalRegisteredDevices() {
    return devices.size();
  }

  // Total active devices
  public long totalActiveDevices() {
    return devices.stream().filter(Device::isActive).count();
  }

  // Check whether any device is inactive
  public boolean hasInactiveDevices() {
    return devices.stream().anyMatch(device -> !device.isActive());
  }

  public boolean areAllDevicesActive() {
    return !devices.isEmpty() && devices.stream().allMatch(Device::isActive);
  }

  public List<Device> findInactiveDevices() {
    return devices.stream().filter(device -> !device.isActive()).toList();
  }
}
