public class HelloJava {
  public static void main(String[] args) {

    String name = "Jaspreet";
    int experience = 12;

    var temperature = 28.5;
    var deviceName = "Sensor-01";
    // deviceName = temperature; // compilation error

    System.out.println("Name: " + name);
    System.out.println("Experience: " + experience + " years");
    System.out.println("I am learning Java!");
    System.out.println("var temperature: " + temperature);
    System.out.println("var deviceName: " + deviceName);
    System.out.println("I am learning Java!");
  }
}
