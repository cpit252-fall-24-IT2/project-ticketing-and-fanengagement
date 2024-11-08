package org.example;

public class Logger {
    //singleton
    private static Logger instance;

    //constructor
    private Logger() {

    }

    // singleton method
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    //Method to log messages
    public void log(String message) {
        System.out.println("LOG: " + message);
    }
}
