package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ValidationUtils {
    private final Scanner scanner;

    public ValidationUtils(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getValidatedInteger(String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if (value >= min && value <= max) {
                    break;
                } else {
                    System.out.println("Error: Please enter a value between " + min + " and " + max + ".");
                }
            } else {
                System.out.println("Error: Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
        return value;
    }

    public String getValidatedDate(String prompt) {
        String dateStr;
        while (true) {
            System.out.print(prompt);
            dateStr = scanner.nextLine();
            try {
                LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if (date.isBefore(LocalDate.now())) {
                    System.out.println("Error: The date cannot be in the past.");
                } else {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Error: Please enter a valid date in the format YYYY-MM-DD.");
            }
        }
        return dateStr;
    }

    public String getValidatedText(String prompt) {
        String text;
        while (true) {
            System.out.print(prompt);
            text = scanner.nextLine().trim();
            if (!text.isEmpty()) {
                break;
            } else {
                System.out.println("Error: This field cannot be empty.");
            }
        }
        return text;
    }
    public double getValidatedDouble(String prompt) {
        double value;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                value = scanner.nextDouble();
                scanner.nextLine(); // Consume the newline character
                break;
            } else {
                System.out.println("Error: Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
        return value;
    }



}
