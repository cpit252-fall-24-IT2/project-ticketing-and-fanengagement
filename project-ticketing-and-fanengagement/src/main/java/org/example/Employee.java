package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Employee {
    private String username;
    private String password;

    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
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
}