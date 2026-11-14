package com.monitoring.device.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.monitoring.device.exception.DuplicateDeviceException;
import com.monitoring.device.model.Device;
import com.monitoring.device.repository.DeviceRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

// Enables Mockito support in JUnit 5 tests.
@ExtendWith(MockitoExtension.class)
class DeviceServiceTest {

  // Creates a mock repository instead of using real storage.
  @Mock private DeviceRepository deviceRepository;

  // Creates the real service and injects the mocked repository.
  @InjectMocks private DeviceService deviceService;

  private Device device;

  // Runs before every test to create fresh test data.
  @BeforeEach
  void setUp() {
    device = new Device(101L, "Temperature Sensor", "TEMPERATURE");
  }

  // Verifies that the service returns devices supplied by the repository.
  @Test
  void shouldReturnAllDevices() {
    when(deviceRepository.findAll()).thenReturn(List.of(device));

    List<Device> result = deviceService.getAllDevices();

    assertEquals(1, result.size());
    assertSame(device, result.get(0));
    verify(deviceRepository).findAll();
  }

  // Verifies that a new device is saved through the repository.
  @Test
  void shouldCreateDevice() {
    when(deviceRepository.findById(101L)).thenReturn(Optional.empty());
    when(deviceRepository.save(device)).thenReturn(device);

    Device result = deviceService.createDevice(device);

    assertSame(device, result);
    verify(deviceRepository).save(device);
  }

  // Verifies that duplicate IDs are rejected without saving.
  @Test
  void shouldRejectDuplicateDeviceId() {
    when(deviceRepository.findById(101L)).thenReturn(Optional.of(device));

    assertThrows(DuplicateDeviceException.class, () -> deviceService.createDevice(device));

    // Confirms that the repository was not asked to save the duplicate.
    verify(deviceRepository, never()).save(device);
  }

  // Verifies that an existing device can be updated.
  @Test
  void shouldUpdateExistingDevice() {
    when(deviceRepository.findById(101L)).thenReturn(Optional.of(device));

    Optional<Device> result =
        deviceService.updateDevice(101L, "Industrial Temperature Sensor", "INDUSTRIAL_TEMPERATURE");

    assertTrue(result.isPresent());
    assertEquals("Industrial Temperature Sensor", device.getName());
    assertEquals("INDUSTRIAL_TEMPERATURE", device.getType());
    verify(deviceRepository).findById(101L);
  }

  // Verifies that an unknown device cannot be updated.
  @Test
  void shouldNotUpdateMissingDevice() {
    when(deviceRepository.findById(999L)).thenReturn(Optional.empty());

    Optional<Device> result = deviceService.updateDevice(999L, "Unknown Sensor", "TEMPERATURE");

    assertFalse(result.isPresent());
    verify(deviceRepository, never()).save(device);
  }

  // Verifies that deletion is delegated to the repository.
  @Test
  void shouldDeleteDevice() {
    when(deviceRepository.deleteById(101L)).thenReturn(true);

    boolean result = deviceService.deleteDevice(101L);

    assertTrue(result);
    verify(deviceRepository).deleteById(101L);
  }
}
