package org.example;

import java.util.Scanner;

public class EmployeeMenu {
    private final Employee employee;
    private final EventManager eventManager;
    private final Scanner scanner;
    private final ValidationUtils validationUtils; // Add this

    public EmployeeMenu(Employee employee, EventManager eventManager, Scanner scanner) {
        this.employee = employee;
        this.eventManager = eventManager;
        this.scanner = scanner;
        this.validationUtils = new ValidationUtils(scanner); // Initialize ValidationUtils
    }

    public void show() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (!employee.authenticate(username, password)) {
            System.out.println("Invalid credentials.");
            return;
        }

        System.out.println("Welcome, Employee!");
        boolean employeeExit = false;

        while (!employeeExit) {
            System.out.println("\nEmployee Menu:");
            System.out.println("1. Add Event");
            System.out.println("2. Remove Event");
            System.out.println("3. View Events");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addEvent();
                case 2 -> removeEvent();
                case 3 -> eventManager.loadEvents();
                case 4 -> employeeExit = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addEvent() {
        System.out.print("Event name: ");
        String name = scanner.nextLine();

        System.out.print("Event location: ");
        String location = scanner.nextLine();

        System.out.print("Event date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        double price = validationUtils.getValidatedDouble("Event price: "); // Use ValidationUtils
        int seats = validationUtils.getValidatedInteger("Total seats: ", 1, 500);

        eventManager.addEvent(name, location, date, price, seats);

    }

    private void removeEvent() {
        int eventId = validationUtils.getValidatedInteger("Enter the Event ID to remove: ", 1, Integer.MAX_VALUE);
        eventManager.removeEvent(eventId);
    }
}
