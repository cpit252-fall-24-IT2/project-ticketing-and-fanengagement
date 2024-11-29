package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Employee {
    private String username;
    private String password;



    public Employee() {
        // Load credentials from environment variables
        this.username = System.getenv("EMPLOYEE_USERNAME");
        this.password = System.getenv("EMPLOYEE_PASSWORD");

        if (username == null || password == null) {
            throw new IllegalStateException("Environment variables for EMPLOYEE_USERNAME and EMPLOYEE_PASSWORD must be set.");
        }
    }


// Authenticate the employee
    public boolean authenticate(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }

    public void addEvent(ArrayList<Event> events) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter event name: ");
        String eventName = scanner.nextLine();

        System.out.print("Enter event location: ");
        String eventLocation = scanner.nextLine();

        System.out.print("Enter event date (YYYY-MM-DD): ");
        String eventDate = scanner.nextLine();

        int eventSeats;
        do {
            System.out.print("Enter number of seats: ");
            eventSeats = scanner.nextInt();
            if (eventSeats <= 0) {
                System.out.println("Error: Number of seats must be greater than 0.");
            }
        } while (eventSeats <= 0);

        double eventPrice;
        do {
            System.out.print("Enter ticket price : ");
            eventPrice = scanner.nextDouble();
            if (eventPrice <= 0) {
                System.out.println("Error: Ticket price must be greater than 0.");
            }
        } while (eventPrice <= 0);

        int newEventId = events.size() + 1;
        Event newEvent = new Event(newEventId, eventLocation, eventDate, eventName, eventSeats, eventPrice);
        events.add(newEvent);

        System.out.println("Event added successfully!");
    }

    public void removeEvent(ArrayList<Event> events, Scanner scanner) {
        if (events.isEmpty()) {
            System.out.println("No events available to remove.");
            return;
        }

        System.out.println("Available Events:");
        for (Event event : events) {
            System.out.println(event.getId() + ". " + event.getName() + " at " + event.getLocation() + " on " + event.getDate());
        }

        System.out.print("Enter the ID of the event you want to remove: ");
        int eventId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        boolean removed = false;
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId() == eventId) {
                events.remove(i);
                removed = true;
                System.out.println("Event removed successfully!");
                break;
            }
        }

        if (!removed) {
            System.out.println("Event ID not found. Please try again.");
        }
    }



}