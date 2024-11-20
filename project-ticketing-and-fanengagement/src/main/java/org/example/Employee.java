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

    // Add a new event
    public void addEvent(ArrayList<Event> events) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter event name: ");
        String eventName = scanner.nextLine();

        System.out.print("Enter event location: ");
        String eventLocation = scanner.nextLine();

        System.out.print("Enter event date (YYYY-MM-DD): ");
        String eventDate = scanner.nextLine();

        System.out.print("Enter number of seats: ");
        int eventSeats = scanner.nextInt();

        System.out.print("Enter ticket price: ");
        double eventPrice = scanner.nextDouble();

        int newEventId = events.size() + 1;
        Event newEvent = new Event(newEventId, eventLocation, eventDate, eventName, eventSeats, eventPrice);
        events.add(newEvent);

        System.out.println("Event added successfully!");
    }
}
