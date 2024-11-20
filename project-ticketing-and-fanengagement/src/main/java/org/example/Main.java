package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EventManager eventManager = new EventManager();

        // Sample Events
        Event event1 = new Event(1, "Stadium A", "2024-12-25", "Concert", 100, 50.0);
        Event event2 = new Event(2, "Stadium B", "2024-12-31", "Football Match", 150, 30.0);

        // Get customer information
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        // Create customer object
        Customer customer = new Customer(name, email);

        boolean exit = false;

        while (!exit) {
            // Display menu
            System.out.println("\nMenu:");
            System.out.println("1. Book Event");
            System.out.println("2. View Bookings");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Book an event
                    System.out.println("\nAvailable Events:");
                    System.out.println("1. " + event1.getName() + " at " + event1.getLocation() + " on " + event1.getDate() + " for " + event1.getPrice() + " SAR");
                    System.out.println("2. " + event2.getName() + " at " + event2.getLocation() + " on " + event2.getDate() + " for " + event2.getPrice() + " SAR");
                    System.out.print("Choose an event (1 or 2): ");
                    int eventChoice = scanner.nextInt();
                    Event selectedEvent = (eventChoice == 1) ? event1 : event2;

                    System.out.println("\nAvailable seats for " + selectedEvent.getName() + ": " + selectedEvent.getAvailableSeats());
                    System.out.print("Enter seat number to book: ");
                    int seatNumber = scanner.nextInt();

                    if (eventManager.bookEvent(customer, selectedEvent, seatNumber)) {
                        System.out.println("Booking successful!");
                    } else {
                        System.out.println("Booking failed. Seat may not be available.");
                    }
                    break;

                case 2:
                    // View customer's bookings
                    eventManager.displayCustomerBookings(customer);
                    break;

                case 3:
                    // Exit program
                    exit = true;
                    System.out.println("Thank you for using the Ticket Booking System!");
                    break;

                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
                    break;
            }
        }

        // Close the scanner
        scanner.close();
    }
}
