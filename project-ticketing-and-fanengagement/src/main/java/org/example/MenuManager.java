package org.example;

import java.util.Scanner;

public class MenuManager {
    private final Scanner scanner = new Scanner(System.in);
    private final Employee employee = new Employee("saleh", "1234");
    private final EventManager eventManager = new EventManager();

    public void start() {
        boolean exit = false;

        while (!exit) {
            try {
                System.out.println("\nMain Menu:");
                System.out.println("1. Employee");
                System.out.println("2. Customer");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                // Wrap input in a try-catch to handle non-integer inputs
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> handleEmployeeMenu();
                    case 2 -> handleCustomerMenu();
                    case 3 -> exit = true;
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }

        System.out.println("Goodbye!");
    }

    private void handleEmployeeMenu() {
        EmployeeMenu employeeMenu = new EmployeeMenu(employee, eventManager, scanner);
        employeeMenu.show();
    }

    private void handleCustomerMenu() {
        CustomerMenu customerMenu = new CustomerMenu(eventManager, scanner);
        customerMenu.show();
    }
}
