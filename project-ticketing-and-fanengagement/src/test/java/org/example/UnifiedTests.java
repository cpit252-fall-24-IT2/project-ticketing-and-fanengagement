package org.example;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class UnifiedTests {

    // Employee Tests
    @Test
    void testEmployeeAuthenticationSuccess() {
        Employee employee = new Employee("admin", "password");
        assertTrue(employee.authenticate("admin", "password"));
    }

    @Test
    void testEmployeeAuthenticationFailure() {
        Employee employee = new Employee("admin", "password");
        assertFalse(employee.authenticate("user", "wrongpassword"));
    }

    // Product Tests
    @Test
    void testProductCreation() {
        Product product = new Product("Event Ticket", 100.0);
        assertEquals("Event Ticket", product.getName());
        assertEquals(100.0, product.getPrice());
    }

    // EventManager Tests



    // DatabaseManager Tests
    @Test
    void testDatabaseManagerSingleton() {
        DatabaseManager instance1 = DatabaseManager.getInstance();
        DatabaseManager instance2 = DatabaseManager.getInstance();
        assertSame(instance1, instance2, "Instances should be the same (Singleton).");
    }

    @Test
    void testDatabaseConnectionNotNull() {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        Connection connection = databaseManager.getConnection();
        assertNotNull(connection);
    }



    @Test
    void testCustomerMenuShowAndExit() {
        // Simulate inputs: "John Doe\njohn.doe@example.com\n3\n"
        String input = "John Doe\njohn.doe@example.com\n3\n"; // Name, email, and exit choice
        Scanner scanner = new Scanner(input); // Simulate user input
        EventManager eventManager = new EventManager();
        CustomerMenu customerMenu = new CustomerMenu(eventManager, scanner);

        customerMenu.show();

        assertTrue(true); // Test passes if no exceptions occur
    }


}
