package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Event> events = new ArrayList<>();
        Employee employee = new Employee("saleh", "1234"); // Example credentials
        EventManager eventManager = new EventManager();

        boolean exit = false;

        while (!exit) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Employee");
            System.out.println("2. Customer");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 2 -> {
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter your email: ");
                    String email = scanner.nextLine();

                    Customer customer = new Customer(name, email);

                    boolean customerExit = false;
                    while (!customerExit) {
                        System.out.println("\nCustomer Menu:");
                        System.out.println("1. Book Event");
                        System.out.println("2. View Bookings");
                        System.out.println("3. Back to Main Menu");
                        System.out.print("Enter your choice: ");
                        int customerChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (customerChoice) {
                            case 1 -> {
                                if (events.isEmpty()) {
                                    System.out.println("No events available.");
                                } else {
                                    System.out.println("\nAvailable Events:");
                                    for (Event event : events) {
                                        System.out.println(event.getId() + ". " + event.getName() + " at " + event.getLocation() + " on " + event.getDate() + " for " + event.getPrice() + " SAR");
                                    }
                                    System.out.print("Choose an event by ID: ");
                                    int eventChoice = scanner.nextInt();
                                    scanner.nextLine();
                                    Event selectedEvent = null;
                                    for (Event event : events) {
                                        if (event.getId() == eventChoice) {
                                            selectedEvent = event;
                                            break;
                                        }
                                    }
                                    if (selectedEvent == null) {
                                        System.out.println("Invalid event ID!");
                                    } else {
                                        System.out.println("Available seats for " + selectedEvent.getName() + ": " + selectedEvent.getAvailableSeats());
                                        System.out.print("Enter seat number to book: ");
                                        int seatNumber = scanner.nextInt();

                                        if (eventManager.bookEvent(customer, selectedEvent, seatNumber)) {
                                            System.out.println("Booking successful!");
                                        } else {
                                            System.out.println("Booking failed. Seat may not be available.");
                                        }
                                    }
                                }
                            }
                            case 2 -> eventManager.displayCustomerBookings(customer);
                            case 3 -> customerExit = true;
                            default -> System.out.println("Invalid choice!");
                        }
                    }
                }
                case 1 -> {
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    if (employee.authenticate(username, password)) {
                        boolean employeeExit = false;
                        while (!employeeExit) {
                            System.out.println("\nEmployee Menu:");
                            System.out.println("1. Add Event");
                            System.out.println("2. Back to Main Menu");
                            System.out.print("Enter your choice: ");
                            int employeeChoice = scanner.nextInt();
                            scanner.nextLine();

                            switch (employeeChoice) {
                                case 1 -> employee.addEvent(events);
                                case 2 -> employeeExit = true;
                                default -> System.out.println("Invalid choice!");
                            }
                        }
                    } else {
                        System.out.println("Invalid credentials!");
                    }
                }
                case 3 -> exit = true;
                default -> System.out.println("Invalid choice!");
            }
        }

        System.out.println("Goodbye!");
        System.out.println("Thank you for using the Ticket Booking System!");
    }
}
