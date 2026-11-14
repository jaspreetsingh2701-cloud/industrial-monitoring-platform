import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DeviceServiceTest {

  @Test
  void shouldRegisterDeviceAndCountIt() {
    DeviceService service = new DeviceService();

    service.register(new Device(101, "Boiler"));

    assertEquals(1, service.totalRegisteredDevices());
  }

  @Test
  void shouldFindRegisteredDevice() {
    DeviceService service = new DeviceService();
    service.register(new Device(101, "Boiler"));

    assertTrue(service.findById(101).isPresent());
    assertEquals("Boiler", service.findById(101).orElseThrow().getName());
  }

  @Test
  void shouldReturnEmptyWhenDeviceDoesNotExist() {
    DeviceService service = new DeviceService();

    assertTrue(service.findById(999).isEmpty());
  }

  @Test
  void shouldReturnOnlyActiveDevices() {
    DeviceService service = new DeviceService();

    Device boiler = new Device(101, "Boiler");
    Device pipeline = new Device(102, "Pipeline");
    pipeline.deactivate();

    service.register(boiler);
    service.register(pipeline);

    assertEquals(1, service.findActiveDevices().size());
    assertEquals("Boiler", service.findActiveDevices().get(0).getName());
  }

  @Test
  void shouldCountActiveDevices() {
    DeviceService service = new DeviceService();

    service.register(new Device(101, "Boiler"));

    Device pipeline = new Device(102, "Pipeline");
    pipeline.deactivate();
    service.register(pipeline);

    assertEquals(1, service.totalActiveDevices());
    assertTrue(service.hasInactiveDevices());
  }
}
