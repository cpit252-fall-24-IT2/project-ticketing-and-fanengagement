package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    int seatNumber;
    private final Map<Customer, List<Event>> customerBookings = new HashMap<>();

    // Book an event for a customer
    public boolean bookEvent(Customer customer, Event event, int seatNumber) {
        if (event.bookSeat(seatNumber)) {
            customerBookings.computeIfAbsent(customer, c -> new ArrayList<>()).add(event);
            Logger.getInstance().log("Customer " + customer.getName() + " booked seat " + seatNumber + " for the event: " + event.getName());
            return true;
        } else {
            Logger.getInstance().logWarning("Seat " + seatNumber + " for the event: " + event.getName() + " is not available for customer: " + customer.getName());
            return false;
        }
    }
    public void displayCustomerBookings(Customer customer) {
        List<Event> bookings = customerBookings.get(customer);

        if (bookings == null || bookings.isEmpty()) {
            System.out.println("No bookings found for customer: " + customer.getName());
        } else {
            System.out.println("Bookings for customer: " + customer.getName());
            for (Event event : bookings) {
                System.out.println("- Event: " + event.getName() + ", Date: " + event.getDate() + ", Location: " + event.getLocation());
            }
        }
    }


    // Retrieve all booked events for a customer
    public List<Event> getBookedEvents(Customer customer) {
        return customerBookings.getOrDefault(customer, new ArrayList<>());
    }


}

