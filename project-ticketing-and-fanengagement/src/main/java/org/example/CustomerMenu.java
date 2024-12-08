package org.example;

import java.util.List;
import java.util.Scanner;

public class CustomerMenu {
    private final EventManager eventManager;
    private final Scanner scanner;
    private final ValidationUtils validator;

    public CustomerMenu(EventManager eventManager, Scanner scanner) {
        this.eventManager = eventManager;
        this.scanner = scanner;
        this.validator = new ValidationUtils(scanner); // Initialize validation utils
    }

    public void show() {
        String name = validator.getValidatedText("Enter your name: ");
        String email = validator.getValidatedText("Enter your email: ");

        System.out.println("Welcome, " + name + "!");
        boolean customerExit = false;

        while (!customerExit) {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. View Events");
            System.out.println("2. Book Ticket");
            System.out.println("3. Exit to Main Menu");
            System.out.print("Enter your choice: ");
            int customerChoice = scanner.nextInt();
            scanner.nextLine();

            switch (customerChoice) {
                case 1 -> {
                    System.out.println("\nAvailable Events:");
                    eventManager.loadEvents();
                }
                case 2 -> bookTicket(name, email);
                case 3 -> customerExit = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void bookTicket(String name, String email) {
        System.out.println("\nAvailable Events:");
        eventManager.loadEvents();

        int eventId = validator.getValidatedInteger("Enter the Event ID to book: ", 1, Integer.MAX_VALUE);
        if (!eventManager.isEventValid(eventId)) {
            System.out.println("Error: Invalid Event ID.");
            return;
        }

        int seatNumber = validator.getValidatedInteger("Enter the seat number: ", 1, 200);
        if (!eventManager.isSeatAvailable(eventId, seatNumber)) {
            System.out.println("Error: Seat already booked or invalid.");
            return;
        }

        boolean bookingSuccess = eventManager.bookEvent(name, email, eventId, seatNumber);

        if (bookingSuccess) {
            System.out.println("Ticket booked successfully!");
            selectTicketView(name, email, eventId, seatNumber);
        } else {
            System.out.println("Booking failed. Please try again.");
        }
    }
    private void selectTicketView(String name, String email, int eventId, int seatNumber) {
        System.out.println("\nHow would you like to view your ticket?");
        System.out.println("1. View in Terminal");
        System.out.println("2. Send via Email");
        System.out.println("3. Download as PDF");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        // Prepare ticket details
        Product ticket = new Product("Event #" + eventId + " - Seat " + seatNumber, 100.00); // Replace price with actual event price
        List<Product> ticketDetails = List.of(ticket);

        switch (choice) {
            case 1 -> {
                // Display in Terminal
                TerminalReceipt terminalReceipt = new TerminalReceipt(ticketDetails);
                terminalReceipt.generate();
            }
            case 2 -> {
                // Send via Email
                EmailReceipt emailReceipt = new EmailReceipt("Your Ticket Details", email, ticketDetails);
                emailReceipt.generate();
            }
            case 3 -> {
                // Download as PDF
                String fileName = "Ticket_" + name.replaceAll("\\s", "_") + ".pdf";
                PDFReceipt pdfReceipt = new PDFReceipt("Your Ticket Details", fileName, ticketDetails);
                pdfReceipt.generate();
                System.out.println("PDF ticket downloaded: " + fileName);
            }
            default -> System.out.println("Invalid choice. No ticket was generated.");
        }
    }

}
