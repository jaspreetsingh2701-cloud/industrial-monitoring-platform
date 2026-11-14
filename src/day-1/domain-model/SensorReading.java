import java.time.LocalDateTime;

public class SensorReading {
  private final int deviceId;
  private final double value;
  private final LocalDateTime timestamp;

  public SensorReading(int deviceId, double value) {
    this.deviceId = deviceId;
    this.value = value;
    this.timestamp = LocalDateTime.now();
  }

  public int getDeviceId() {
    return deviceId;
  }

  public double getValue() {
    return value;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }
}
