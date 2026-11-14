public class Main {

    public static void main(String[] args) {

        DeviceService service = new DeviceService();

        // Register devices
        service.register(new Device(101, "Boiler Temperature"));
        service.register(new Device(102, "Pipeline Pressure"));
        service.register(new Device(103, "Tank Humidity"));
        service.register(new Device(104, "Cooling System"));

        // Find device
        service.findById(102).ifPresent(System.out::println);

        // Deactivate device 103
        service.findById(103).ifPresent(Device::deactivate);

        // Display active devices
        System.out.println("Active devices:");

        service.findActiveDevices().forEach(System.out::println);

        System.out.println("Total registered: " + service.totalRegisteredDevices());

        System.out.println("Total active: " + service.totalActiveDevices());

        System.out.println("Has inactive devices: " + service.hasInactiveDevices());

        System.out.println("All devices active: " + service.areAllDevicesActive());

        // Search for a missing device
        System.out.println("Missing device:");

        System.out.println(
            service.findById(999)
                .map(Device::getName)
                .orElse("Not found")
        );
    }
}