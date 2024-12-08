package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInitializer {
    public static void initialize() {
        String createEventsTable = """
            CREATE TABLE IF NOT EXISTS events (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                location TEXT NOT NULL,
                date TEXT NOT NULL,
                price REAL NOT NULL,
                total_seats INTEGER NOT NULL
            );
        """;



        String createBookingsTable = """
            CREATE TABLE IF NOT EXISTS bookings (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                customer_name TEXT NOT NULL,
                customer_email TEXT NOT NULL,
                event_id INTEGER NOT NULL,
                seat_number INTEGER NOT NULL,
                FOREIGN KEY (event_id) REFERENCES events (id)
            );
        """;

        try (Connection conn = DatabaseManager.getInstance().getConnection();

             PreparedStatement createEventsStmt = conn.prepareStatement(createEventsTable);
             PreparedStatement createBookingsStmt = conn.prepareStatement(createBookingsTable)) {
            createEventsStmt.execute();
            createBookingsStmt.execute();
            System.out.println("Tables initialized successfully.");
        } catch (SQLException e) {
            System.err.println("Error initializing database: " + e.getMessage());
        }
    }
}
