package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventManager {

    // Load all events from the database
    public void loadEvents() {
        String query = "SELECT id, name, location, date, price, total_seats FROM events";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("\nAvailable Events:");
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s, Location: %s, Date: %s, Price: %.2f, Seats: %d%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getString("date"),
                        rs.getDouble("price"),
                        rs.getInt("total_seats"));
            }
        } catch (SQLException e) {
            System.err.println("Error loading events: " + e.getMessage());
        }
    }

    // Add a new event to the database
    public void addEvent(String name, String location, String date, double price, int totalSeats) {
        String query = "INSERT INTO events (name, location, date, price, total_seats) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            if (conn.isClosed()) {
                System.err.println("Error: Database connection is closed!");
                return;
            }

            pstmt.setString(1, name);
            pstmt.setString(2, location);
            pstmt.setString(3, date);
            pstmt.setDouble(4, price);
            pstmt.setInt(5, totalSeats);
            pstmt.executeUpdate();
            System.out.println("Event added successfully.");
        } catch (SQLException e) {
            System.err.println("Error adding event: " + e.getMessage());
        }
    }

    // Remove an event from the database by ID
    public void removeEvent(int eventId) {
        String query = "DELETE FROM events WHERE id = ?";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, eventId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Event removed successfully.");
            } else {
                System.out.println("No event found with the specified ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error removing event: " + e.getMessage());
        }
    }

    public boolean bookEvent(String customerName, String customerEmail, int eventId, int seatNumber) {
        // Check if the event exists
        String checkEventQuery = "SELECT total_seats FROM events WHERE id = ?";
        String checkSeatQuery = "SELECT COUNT(*) AS count FROM bookings WHERE event_id = ? AND seat_number = ?";
        String insertBookingQuery = """
            INSERT INTO bookings (customer_name, customer_email, event_id, seat_number)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseManager.getInstance().getConnection()) {
            // Check if event exists and seat is valid
            try (PreparedStatement checkEventStmt = conn.prepareStatement(checkEventQuery);
                 PreparedStatement checkSeatStmt = conn.prepareStatement(checkSeatQuery)) {
                checkEventStmt.setInt(1, eventId);
                ResultSet eventResult = checkEventStmt.executeQuery();

                if (!eventResult.next()) {
                    System.out.println("Event ID not found.");
                    return false;
                }

                int totalSeats = eventResult.getInt("total_seats");
                if (seatNumber < 1 || seatNumber > totalSeats) {
                    System.out.println("Invalid seat number.");
                    return false;
                }

                checkSeatStmt.setInt(1, eventId);
                checkSeatStmt.setInt(2, seatNumber);
                ResultSet seatResult = checkSeatStmt.executeQuery();

                if (seatResult.next() && seatResult.getInt("count") > 0) {
                    System.out.println("Seat is already booked.");
                    return false;
                }
            }

            // Book the seat
            try (PreparedStatement insertBookingStmt = conn.prepareStatement(insertBookingQuery)) {
                insertBookingStmt.setString(1, customerName);
                insertBookingStmt.setString(2, customerEmail);
                insertBookingStmt.setInt(3, eventId);
                insertBookingStmt.setInt(4, seatNumber);
                insertBookingStmt.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error booking event: " + e.getMessage());
            return false;
        }
    }
    public boolean isEventValid(int eventId) {
        String query = "SELECT COUNT(*) AS count FROM events WHERE id = ?";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, eventId);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() && rs.getInt("count") > 0;
        } catch (SQLException e) {
            System.err.println("Error checking event validity: " + e.getMessage());
            return false;
        }
    }

    public boolean isSeatAvailable(int eventId, int seatNumber) {
        String query = "SELECT COUNT(*) AS count FROM bookings WHERE event_id = ? AND seat_number = ?";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, eventId);
            pstmt.setInt(2, seatNumber);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() && rs.getInt("count") == 0;
        } catch (SQLException e) {
            System.err.println("Error checking seat availability: " + e.getMessage());
            return false;
        }
    }

}
