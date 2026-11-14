import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {

    // Uncomment the exercise you want to execute.

    // Exercise 1: Device registration, lookup and status
    // runDeviceOperations();
    // Exercise 2: orElse() vs orElseGet()
    // runOptionalOrElseDemo();
    // Exercise 3: Sensor readings
    runSensorReadingDemo();
  }

  // Exercise 1: Device registration, lookup and status
  public static void runDeviceOperations() {

    DeviceService service = new DeviceService();

    // Register devices
    service.register(new Device(101, "Boiler Temperature"));
    service.register(new Device(102, "Pipeline Pressure"));
    service.register(new Device(103, "Tank Humidity"));
    service.register(new Device(104, "Cooling System"));

    // Find device
    service.findById(102).ifPresent(System.out::println);

    // Deactivate devices
    service.findById(103).ifPresent(Device::deactivate);
    service.findById(101).ifPresent(Device::deactivate);

    // Display active devices
    System.out.println("Active devices:");
    service.findActiveDevices().forEach(System.out::println);

    // Device statistics
    System.out.println("Total registered: " + service.totalRegisteredDevices());
    System.out.println("Total active: " + service.totalActiveDevices());
    System.out.println("Has inactive devices: " + service.hasInactiveDevices());
    System.out.println("All devices active: " + service.areAllDevicesActive());

    // Optional lookup
    System.out.println("Device 101 name:");
    String deviceName = service.findById(101).map(Device::getName).orElse("Device not found");

    System.out.println(deviceName);

    System.out.println("Missing device:");
    System.out.println(service.findById(999).map(Device::getName).orElse("Not found"));
  }

  // Exercise 2: orElse() vs orElseGet()
  public static void runOptionalOrElseDemo() {

    DeviceService service = new DeviceService();
    service.register(new Device(102, "Pipeline Pressure"));

    String name = service.findById(102).map(Device::getName).orElse(getDefaultDeviceName());

    System.out.println(name);
  }

  public static String getDefaultDeviceName() {
    System.out.println("Generating default name...");
    return "Unknown Device";
  }

  // Exercise 3: Sensor readings
  public static void runSensorReadingDemo() {

    List<SensorReading> readings = new ArrayList<>();
    List<SensorReading> emptyReadings = new ArrayList<>();

    readings.add(new SensorReading(101, 28.5));
    readings.add(new SensorReading(101, 30.2));
    readings.add(new SensorReading(101, 32.1));

    double average = readings.stream().mapToDouble(SensorReading::getValue).average().orElse(0.0);

    System.out.println("Average temperature: " + average);

    double emptyAverage =
        emptyReadings.stream().mapToDouble(SensorReading::getValue).average().orElse(0.0);

    System.out.println("Empty readings average: " + emptyAverage);
  }
}
