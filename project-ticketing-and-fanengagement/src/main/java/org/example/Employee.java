package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

        String eventDate;
        while (true) {
            System.out.print("Enter event date (YYYY-MM-DD): ");
            eventDate = scanner.nextLine();

            // Validate the date format and check if it's not in the past
            if (isValidDate(eventDate)) {
                break;
            } else {
                System.out.println("Invalid date. Please enter a valid future date in the format YYYY-MM-DD.");
            }
        }

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

    private boolean isValidDate(String dateStr) {
        try {
            // Parse the date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dateStr, formatter);

            // Check if the date is not in the past
            return !date.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false; // Invalid date format
        }
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