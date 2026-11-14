public class Device {

  private final int id; // final means ID and name cannot be reassigned after construction
  private final String name; // final means ID and name cannot be reassigned after construction
  private boolean active;

  // to initializes the device
  public Device(int id, String name) {
    this.id = id;
    this.name = name;
    this.active = true;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public boolean isActive() {
    return active;
  }

  // controls how active status changes
  public void deactivate() {
    this.active = false;
  }

  // defines how the object is displayed as text
  @Override
  public String toString() {
    return id + " - " + name + " (active: " + active + ")";
  }
}
