import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DeviceTest {

  @Test
  void newDeviceShouldBeActive() {

    Device device = new Device(101, "Boiler");

    assertTrue(device.isActive());
  }

  @Test
  void deviceShouldBeDeactivated() {

    Device device = new Device(102, "Pipeline");

    device.deactivate();

    assertFalse(device.isActive());
  }

  @Test
  void deviceShouldReturnCorrectName() {

    Device device = new Device(103, "Cooling System");

    assertEquals("Cooling System", device.getName());
  }
}
