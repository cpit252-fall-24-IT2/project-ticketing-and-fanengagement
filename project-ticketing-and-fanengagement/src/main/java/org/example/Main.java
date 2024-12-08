package org.example;

public class Main {
    public static void main(String[] args) {
        // Initialize the database and create tables
        DatabaseInitializer.initialize();

        // Add shutdown hook to close the database connection
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            DatabaseManager.getInstance().closeConnection();
        }));

        // Start the main menu
        MenuManager menuManager = new MenuManager();
        menuManager.start();
    }
}
