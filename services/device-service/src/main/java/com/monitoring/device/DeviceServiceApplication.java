package com.monitoring.device;

// Enables Spring Boot configuration, auto-configuration and component scanning.
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Marks this as the main Spring Boot application class.
@SpringBootApplication
public class DeviceServiceApplication {

  // Java application entry point.
  public static void main(String[] args) {

    // Bootstraps Spring, creates the application context and starts the server.
    SpringApplication.run(DeviceServiceApplication.class, args);
  }
}
